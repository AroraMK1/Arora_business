package com.ryo.arora.infrastructure.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.ryo.arora.domain.service.AuthService;
import com.ryo.arora.infrastructure.cache.RedisTokenRevocationService;
import com.ryo.arora.infrastructure.persistence.entity.TokenEntity;
import com.ryo.arora.infrastructure.persistence.entity.UserEntity;
import com.ryo.arora.infrastructure.persistence.repository.jpa.UserJpaRepository;
import com.ryo.arora.presentation.dto.auth.condition.LoginConditionEntity;
import com.ryo.arora.shared.exception.AppException;
import com.ryo.arora.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service @Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @NonFinal
    @Value("${jwt.signerKey:key}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration:3600}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration:7200}")
    protected long REFRESHABLE_DURATION;

    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userJpaRepository;
    private final RedisTokenRevocationService tokenRevocationService;

    @Override
    public TokenEntity authenticate(LoginConditionEntity conditionEntity) {
        String email = conditionEntity.getEmail();
        String password = conditionEntity.getPassword();
        UserEntity userEntity = userJpaRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
                        );
        var isValid = passwordEncoder.matches(password, userEntity.getPassword());
        if(isValid){
            return TokenEntity.builder()
                    .accessToken(generateToken(userEntity,false))
                    .refreshToken(generateToken(userEntity,true))
                    .build();
        }
        else{
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    @Override
    public boolean introspect(String token) throws JOSEException, ParseException{
        boolean isValid = true;
        try {
            SignedJWT signedJWT = verifyToken(token, "access");
            String jti = signedJWT.getJWTClaimsSet().getJWTID();
            return !tokenRevocationService.isAccessTokenRevoked(jti);
        } catch (AppException e) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void logout(String accessToken, String refreshToken) {
        try {
            SignedJWT accessJWT = verifyToken(accessToken, "access");
            SignedJWT refreshJWT = verifyToken(refreshToken, "refresh");

            JWTClaimsSet accessClaims = accessJWT.getJWTClaimsSet();
            JWTClaimsSet refreshClaims = refreshJWT.getJWTClaimsSet();

            if (!accessClaims.getSubject().equals(
                    refreshClaims.getSubject())) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }

            tokenRevocationService.revokeAccessToken(
                    accessClaims.getJWTID(),
                    accessClaims.getExpirationTime().toInstant()
            );

            tokenRevocationService.revokeRefreshToken(
                    refreshClaims.getJWTID(),
                    refreshClaims.getExpirationTime().toInstant()
            );

        } catch (AppException e) {
            throw e;
        } catch (JOSEException | ParseException e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

    private SignedJWT verifyToken(String token, String expectedType) throws JOSEException, ParseException {

        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(
                SIGNER_KEY.getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );

        if (!signedJWT.verify(verifier)) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

        Date expiryTime = claims.getExpirationTime();

        if (expiryTime == null
                || !expiryTime.after(new Date())
                || !"arora.com".equals(claims.getIssuer())
                || !expectedType.equals(claims.getStringClaim("token_type"))
                || claims.getJWTID() == null
                || claims.getSubject() == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    private String generateToken(UserEntity user, boolean isRefresh){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        long duration = isRefresh ? REFRESHABLE_DURATION : VALID_DURATION;

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserId().toString())
                .issuer("arora.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now()
                                .plus(duration, ChronoUnit.SECONDS)
                                .toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("email", user.getEmail())
                .claim("token_type", isRefresh ? "refresh" : "access")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
}

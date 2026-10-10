package com.ryo.arora.infrastructure.security;

import com.nimbusds.jose.JOSEException;
import com.ryo.arora.domain.service.AuthService;
import com.ryo.arora.shared.exception.AppException;
import com.ryo.arora.shared.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
@Slf4j
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey:signerKey}")
    private String signerKey;

    private NimbusJwtDecoder nimbusJwtDecoder;

    @Autowired
    private AuthService authService;

    @Override
    public Jwt decode(String token) throws JwtException {
        try{
            boolean valid = authService.introspect(token);
            if (!valid) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }
            if (Objects.isNull(nimbusJwtDecoder)) {
                SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
                nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                        .macAlgorithm(MacAlgorithm.HS512)
                        .build();
            }

            return nimbusJwtDecoder.decode(token);
        }
        catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }
    }
}

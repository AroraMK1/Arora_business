package com.ryo.arora.infrastructure.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class RedisTokenRevocationService {

    private static final String ACCESS_PREFIX =
            "arora:auth:revoked:access:";

    private static final String REFRESH_PREFIX =
            "arora:auth:revoked:refresh:";

    private final StringRedisTemplate redisTemplate;

    public RedisTokenRevocationService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void revokeAccessToken(String jti, Instant expiresAt) {
        revoke(ACCESS_PREFIX, jti, expiresAt);
    }

    public void revokeRefreshToken(String jti, Instant expiresAt) {
        revoke(REFRESH_PREFIX, jti, expiresAt);
    }

    public boolean isAccessTokenRevoked(String jti) {
        return isRevoked(ACCESS_PREFIX, jti);
    }

    public boolean isRefreshTokenRevoked(String jti) {
        return isRevoked(REFRESH_PREFIX, jti);
    }

    private void revoke(String prefix, String jti, Instant expiresAt) {
        if (jti == null || jti.isBlank() || expiresAt == null) {
            throw new IllegalArgumentException(
                    "Token jti and expiration are required"
            );
        }

        long ttlSeconds = Duration.between(
                Instant.now(), expiresAt
        ).getSeconds();

        if (ttlSeconds <= 0) {
            return;
        }

        redisTemplate.opsForValue().set(
                prefix + jti,
                "1",
                Duration.ofSeconds(ttlSeconds)
        );
    }

    private boolean isRevoked(String prefix, String jti) {
        if (jti == null || jti.isBlank()) {
            return true;
        }

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(prefix + jti)
        );
    }
}
package com.project.backend.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class TokenService {
    private final String secret = System.getenv().getOrDefault("JWT_SECRET", "change-me-please");
    private static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds

    public String generateToken(String email) {
        Key key = getSigningKey();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSigningKey() {
        byte[] bytes = secret.getBytes();
        return new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
    }
}

package com.project.backend.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class TokenService {
    private final String secret = System.getenv().getOrDefault("JWT_SECRET", "change-me-please");

    public String generateToken(String email) {
        Key key = getSigningKey();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSigningKey() {
        byte[] bytes = secret.getBytes();
        return new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
    }
}

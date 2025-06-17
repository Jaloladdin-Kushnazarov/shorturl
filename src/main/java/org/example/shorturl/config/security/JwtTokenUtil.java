package org.example.shorturl.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "7134743777217A25432A462D4A614E645267556B58703272357538782F413F44";
    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        // Base64 orqali dekodlash
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public String generateToken(String username) {
        Instant now = Instant.now();
        Instant expiry = now.plus(10, ChronoUnit.MINUTES); // 10 daqiqalik token

        Map<String, Object> claims = Map.of("me", "you");

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .issuer("https://online.pdp.uz")
                .claims(claims)
                .signWith(signingKey) // YANGI: SignatureAlgorithm endi kerak emas
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Claims claims = parseToken(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace(); // yoki logger.warn(...)
            return false;
        }
    }

    public String getUsername(@NonNull String token) {
        return parseToken(token).getSubject();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(signingKey) // YANGI: endi setSigningKey emas, verifyWith ishlatiladi
                .build()
                .parseSignedClaims(token)
                .getPayload(); // getBody() emas, getPayload()
    }
}
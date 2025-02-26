package com.citycite_api.auth.service;

import com.citycite_api.user.entity.AccountType;
import io.jsonwebtoken.*;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@Transactional
@NoArgsConstructor
public class JwtProvider implements iJwtProvider {

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.access-token-expiration}")
    private long expirationTimeMS;

    @Override
    public String generateAccessJWT(Integer userId, AccountType accountType) {
        return Jwts.builder()
                .setSubject(Integer.toString(userId))
                .claim("role", "ROLE_" + accountType.name())
                .setIssuer("CityCite")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMS))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();  // Create and return the token
    }

    @Override
    public Claims getJWTClaims(String jwt) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    @Override
    public Integer extractUserID(Claims jwtClaims) {
        return Integer.parseInt(jwtClaims.getSubject());
    }

    @Override
    public String extractUserRole(Claims jwtClaims) {
            return jwtClaims.get("role", String.class);
    }

    @Override
    public boolean isExpired(Claims jwtClaims) {
            Date expirationDate = jwtClaims.getExpiration();
            return expirationDate.before(new Date());
    }

    @Override
    public String removeBearerPrefix(String rawJwt) throws MalformedJwtException {
        if (rawJwt == null || !rawJwt.startsWith("Bearer ")){
            throw new MalformedJwtException("Invalid JWT provided!");
        }
        return rawJwt.substring(7);
    }

}

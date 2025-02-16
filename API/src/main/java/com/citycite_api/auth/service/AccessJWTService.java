package com.citycite_api.auth.service;

import com.citycite_api.user.entity.AccountType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class AccessJWTService {

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.access-token-expiration}")
    private long accessTokenExpirationMS;

    public String generateAccessJWT(Integer userId, AccountType accountType) {
        return Jwts.builder()
                .setSubject(Integer.toString(userId))  // Set the subject to the user's ID
                .claim("role", accountType)  // Set a custom claim for the user's role
                .setIssuedAt(new Date())  // Set the issued date
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationMS))  // Set expiration time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Sign the token with the secret key
                .compact();  // Create and return the token
    }

    public Claims getJWTClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidSignature(String token) {
        try {
            // Try to parse the JWT and validate its signature
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;  // Signature is valid
        } catch (JwtException e) {
            return false;  // Invalid token (e.g., tampered or expired)
        }
    }

    public Integer extractUserID(String token) {
        try {
            Claims claims = getJWTClaims(token);
            return Integer.parseInt(claims.getSubject());
        } catch (JwtException | NumberFormatException e) {
            // Handle exception (e.g., log it)
            return null;
        }
    }

    public AccountType getUserRoleFromToken(String token) {
        try {
            Claims claims = getJWTClaims(token);
            return AccountType.valueOf(claims.get("role", String.class));
        } catch (JwtException | IllegalArgumentException e) {
            // Handle exception (e.g., log it)
            return null;
        }
    }

    public boolean isExpired(String token) {
        try {
            Claims claims = getJWTClaims(token);  // Extract claims from the token
            Date expirationDate = claims.getExpiration();  // Get the expiration date
            return expirationDate.before(new Date());  // Return true if the token is expired
        } catch (JwtException e) {
            return true;  // In case of any error, assume the token is expired or invalid
        }
    }
}

package com.citycite_api.auth.service;

import com.citycite_api.user.entity.AccountType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iJwtProvider {
    public String generateAccessJWT(Integer userId, AccountType accountType);
    public Claims getJWTClaims(String token);
    public AccountType extractUserRole(Claims jwtClaims);
    public Integer extractUserID(Claims jwtClaims);
    public boolean isExpired(Claims jwtClaims);
    public String removeBearerPrefix(String jwt) throws MalformedJwtException;
}

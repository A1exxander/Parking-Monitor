package com.citycite_api.auth.service;

import com.citycite_api.user.entity.AccountType;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iAccessJWTService {
    public String generateAccessJWT(Integer userId, AccountType accountType);
    public Claims getJWTClaims(String token);
    public boolean isValidSignature(String token);
    public boolean isExpired(String token);
    public Integer extractUserID(String token);
    public AccountType extractUserRole(String token);
}

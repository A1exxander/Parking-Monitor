package com.citycite_api.security.filter;

import com.citycite_api.auth.service.JwtProvider;
import com.citycite_api.user.entity.AccountType;
import com.citycite_api.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import java.io.IOException;
import java.util.Collections;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.length() == 0) {
            throw new AuthenticationCredentialsNotFoundException("Authorization header is missing or empty");
        } // This + all the following potential exceptions should be caught using a global entrypoint exception handler ( Not ControllerAdvice )

        String jwt = jwtService.removeBearerPrefix(authHeader);
        Claims jwtClaims = jwtService.getJWTClaims(jwt);

        Integer userID = jwtService.extractUserID(jwtClaims);
        String userRole = jwtService.extractUserRole(jwtClaims);
        boolean isJwtExpired = jwtService.isExpired(jwtClaims);

        if (isJwtExpired) {
            throw new ExpiredJwtException(null, null, "JWT has expired");
        }
        else if (userID == null || !userService.userExistsByID(userID) || userRole == null || userRole.length() == 0) {
            throw new JwtException("Invalid JWT provided.");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userID,
                null, // As we use JWTs, we do not need to provide credentials
                Collections.singleton(new SimpleGrantedAuthority(userRole))
        );

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }

}
package com.citycite_api.security.filter;

import com.citycite_api.auth.service.JwtProvider;
import com.citycite_api.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isBlank()) {
            throw new AuthenticationCredentialsNotFoundException("Authorization header is missing or empty");
        } // This + all the following potential exceptions should be caught using a global entrypoint exception handler ( Not ControllerAdvice )

        String jwt = jwtProvider.removeBearerPrefix(authHeader);
        Claims jwtClaims = jwtProvider.getJWTClaims(jwt);

        Integer userID = jwtProvider.extractUserID(jwtClaims);
        String userRole = jwtProvider.extractUserRole(jwtClaims);
        boolean isJwtExpired = jwtProvider.isExpired(jwtClaims);

        if (userID == null || userID <= 0 || !userService.userExistsByID(userID) || userRole == null || userRole.isBlank()) {
            throw new JwtException("Invalid JWT provided. UserID or UserRole is invalid.");
        }
        else if (isJwtExpired) {
            throw new ExpiredJwtException(null, null, "Invalid JWT provided. JWT has expired");
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        List<String> excludedPaths = Arrays.asList(
                "/api/v*/auth/**",
                "/api/v*/jurisdictions/**",
                "/swagger-ui/**",
                "/v3/api-docs/**"
        );

        return excludedPaths.stream().anyMatch(pattern -> pathMatcher.match(pattern, request.getServletPath()));

    }

}
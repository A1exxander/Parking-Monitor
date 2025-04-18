package com.citycite_api.security.utils;

import com.citycite_api.user.entity.AccountType;
import org.springframework.security.core.Authentication;

public final class SecurityUtils {

    public static AccountType extractUserRole(Authentication authentication) {

        AccountType accountType = AccountType.valueOf(authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replaceFirst("^ROLE_", "")
                .toUpperCase()); // Should prob move this into its own method, and use ROLE_ for account types in future

        return accountType;

    }

}

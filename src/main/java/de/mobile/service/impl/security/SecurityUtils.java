package de.mobile.service.impl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;

public final class SecurityUtils {

    public static String getUserId() { // unique email is used as userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new UnauthorizedUserException("While getting user id");
        }
        return (String) authentication.getPrincipal();
    }
}

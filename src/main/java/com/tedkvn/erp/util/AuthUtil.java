package com.tedkvn.erp.util;

import com.tedkvn.erp.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthUtil {

    public static String getAuthenticatorUsername() {
        return Optional.ofNullable(getUserDetails()).map(UserDetailsImpl::getUsername)
                .orElse("anonymousUser");
    }

    public static UserDetailsImpl getUserDetails() {
        Object principal = getAuthentication().getPrincipal();
        return principal.equals("anonymousUser") ? null : ((UserDetailsImpl) principal);
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static java.util.Collection<?
            extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        return getAuthentication().getAuthorities();
    }

}

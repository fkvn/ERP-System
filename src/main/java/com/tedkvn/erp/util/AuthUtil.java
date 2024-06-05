package com.tedkvn.erp.util;

import com.tedkvn.erp.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtil {
    public static String getAuthenticatorUsername() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {
            return "anonymousUser";
        }
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername();
    }

    public static UserDetails getUserDetails() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {
            return null;
        }
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal());
    }
}

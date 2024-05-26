package com.tedkvn.erp.util;

import com.tedkvn.erp.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    public static String getAuthenticatorUsername() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {
            return "anonymousUser";
        }
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername();
    }
}

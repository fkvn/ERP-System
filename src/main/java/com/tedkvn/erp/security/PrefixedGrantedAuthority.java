package com.tedkvn.erp.security;

import org.springframework.security.core.GrantedAuthority;

public class PrefixedGrantedAuthority implements GrantedAuthority {

    private final String prefix;
    private final GrantedAuthority authority;

    public PrefixedGrantedAuthority(String prefix, GrantedAuthority authority) {
        this.prefix = prefix;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return prefix + ":" + authority.getAuthority();
    }
}

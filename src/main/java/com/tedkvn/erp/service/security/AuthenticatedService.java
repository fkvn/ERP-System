package com.tedkvn.erp.service.security;

import com.tedkvn.erp.security.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@PreAuthorize("isAuthenticated()")
public class AuthenticatedService {

    public boolean isAdminForStore(Long storeId) {
        Authentication authentication = getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(
                authority -> authority instanceof SimpleGrantedAuthority &&
                        authority.getAuthority().startsWith("STORE_" + storeId + ":ROLE_ADMIN"));
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAdminForWarehouse(Long warehouseId) {
        Authentication authentication = getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(
                authority -> authority instanceof SimpleGrantedAuthority && authority.getAuthority()
                        .startsWith("WAREHOUSE_" + warehouseId + ":ROLE_ADMIN"));
    }

    public boolean isSuperAdmin() {
        Authentication authentication = getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) return false;
        return ((UserDetailsImpl) authentication.getPrincipal()).isSuperAdmin();
    }
}

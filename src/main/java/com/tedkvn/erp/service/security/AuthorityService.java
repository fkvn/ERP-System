package com.tedkvn.erp.service.security;

import com.tedkvn.erp.util.AuthUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@PreAuthorize("isAuthenticated()")
public class AuthorityService {

    public boolean isAdminForStore(Long storeId) {
        return AuthUtil.getAuthorities().stream().anyMatch(
                authority -> authority instanceof SimpleGrantedAuthority &&
                        authority.getAuthority().startsWith("STORE_" + storeId + ":ROLE_ADMIN"));
    }

    public boolean isSuperAdmin() {
        return AuthUtil.getUserDetails().isSuperAdmin();
    }

    public boolean isAdminForWarehouse(Long warehouseId) {
        return AuthUtil.getAuthorities().stream().anyMatch(
                authority -> authority instanceof SimpleGrantedAuthority && authority.getAuthority()
                        .startsWith("WAREHOUSE_" + warehouseId + ":ROLE_ADMIN"));
    }
}

package com.tedkvn.erp.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Override
    public boolean isAdminForStore(Long storeId) {

        return authentication.getAuthorities().stream().anyMatch(
                authority -> authority instanceof SimpleGrantedAuthority &&
                        authority.getAuthority().startsWith("STORE_" + storeId + ":ROLE_ADMIN"));
    }

    @Override
    public boolean isAdminForWarehouse(Long warehouseId) {
        return authentication.getAuthorities().stream().anyMatch(
                authority -> authority instanceof SimpleGrantedAuthority && authority.getAuthority()
                        .startsWith("WAREHOUSE_" + warehouseId + ":ROLE_ADMIN"));
    }
}
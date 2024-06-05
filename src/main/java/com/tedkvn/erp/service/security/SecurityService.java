package com.tedkvn.erp.service.security;

public interface SecurityService {
    
    boolean isAdminForStore(Long storeId);

    boolean isAdminForWarehouse(Long warehouseId);
}

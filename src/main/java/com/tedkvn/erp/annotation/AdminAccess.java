package com.tedkvn.erp.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("(" + "#storeId != null && @securityService.isAdminForStore(#storeId)) || " + "(" +
        "#warehouseId != null && @securityService.isAdminForWarehouse(#warehouseId))")
public @interface AdminAccess {
    long storeId() default -1; // Default to -1 if not provided

    long warehouseId() default -1; // Default to -1 if not provided
}
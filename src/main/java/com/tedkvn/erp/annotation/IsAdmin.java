package com.tedkvn.erp.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("(" + "#storeId != null && @authorityService.isAdminForStore(#storeId)) || " + "(" +
        "#warehouseId != null && @authorityService.isAdminForWarehouse(#warehouseId))")
public @interface IsAdmin {
    long storeId() default -1; // Default to -1 if not provided

    long warehouseId() default -1; // Default to -1 if not provided
}
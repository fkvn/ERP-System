package com.tedkvn.erp.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("@authenticatedService.isSuperAdmin()")
public @interface SuperAdmin {}

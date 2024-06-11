package com.tedkvn.erp.annotation;

import com.tedkvn.erp.validator.RegionCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = RegionCodeValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface IsValidatedRegionCode {
    String message() default "Phone Region must be a valid 2-digit code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
package com.tedkvn.erp.annotation;

import com.tedkvn.erp.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Password {
    String message() default "8 to 20 characters (1 upper, 1 lower, 1 number, and no white space)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
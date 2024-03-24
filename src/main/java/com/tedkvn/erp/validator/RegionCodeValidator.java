package com.tedkvn.erp.validator;

import com.tedkvn.erp.annotation.RegionCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegionCodeValidator implements ConstraintValidator<RegionCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() == 2;
    }
}
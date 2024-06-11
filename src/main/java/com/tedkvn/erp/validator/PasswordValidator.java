package com.tedkvn.erp.validator;

import com.tedkvn.erp.annotation.IsValidatedPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class PasswordValidator implements ConstraintValidator<IsValidatedPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!isBlank(value)) {
            String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.* ).{8,20}$";
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(value);

            return matcher.matches();
        }
        return false;
    }
}
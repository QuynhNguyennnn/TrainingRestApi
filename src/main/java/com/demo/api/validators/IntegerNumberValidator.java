package com.demo.api.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Integer Number Validator.
 * 
 * @author QuynhNN
 */
public class IntegerNumberValidator implements ConstraintValidator<IntegerConstraint, String> {

    /**
     * Initialization function.
     * 
     */
    @Override
    public void initialize(IntegerConstraint integrConstraint) {
    }

    /**
     * Check input is number or not.
     * 
     * @return true/ false.
     */
    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext context) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() > 1 && (contactField.length() < 3));
    }
}

package com.demo.api.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Integer Constraint.
 * 
 * @author QuynhNN
 */
@Documented
@Constraint(validatedBy = IntegerNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerConstraint {
    String message() default "Invalid id number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

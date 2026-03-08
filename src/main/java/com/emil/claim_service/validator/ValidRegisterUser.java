package com.emil.claim_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegisterUserValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegisterUser {

    String message() default "Invalid user registration request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
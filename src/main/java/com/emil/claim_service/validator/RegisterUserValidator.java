package com.emil.claim_service.validator;

import com.emil.claim_service.dto.request.RegisterUserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class RegisterUserValidator implements ConstraintValidator<ValidRegisterUser, RegisterUserRequest> {

    @Override
    public boolean isValid(RegisterUserRequest value, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation();

        List<String> errors = new ArrayList<>();

        if (value.getName() == null || value.getName().isBlank()) {
            errors.add("Name is required");
        }

        if (value.getEmail() == null || value.getEmail().isBlank()) {
            errors.add("Email is required");
        } else if (!value.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("Invalid email format");
        }


        errors.forEach(error ->
                context.buildConstraintViolationWithTemplate(error)
                        .addConstraintViolation()
        );

        return errors.isEmpty();
    }
}
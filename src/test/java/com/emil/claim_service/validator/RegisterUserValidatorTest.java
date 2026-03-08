package com.emil.claim_service.validator;

import com.emil.claim_service.dto.request.RegisterUserRequest;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUserValidatorTest {

    @InjectMocks
    private RegisterUserValidator validator;

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder builder;

    @BeforeEach
    void setUp() {
            }


    @Test
    void isValid_ShouldReturnFalse_WhenNameIsMissing() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("test@example.com");
        request.setUsername("testuser");

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addConstraintViolation()).thenReturn(context);

        assertFalse(validator.isValid(request, context));
    }

    @Test
    void isValid_ShouldReturnFalse_WhenEmailIsMissing() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("Test User");
        request.setUsername("testuser");

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addConstraintViolation()).thenReturn(context);

        assertFalse(validator.isValid(request, context));
    }

    @Test
    void isValid_ShouldReturnFalse_WhenEmailIsInvalid() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("Test User");
        request.setEmail("invalid-email");
        request.setUsername("testuser");

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addConstraintViolation()).thenReturn(context);

        assertFalse(validator.isValid(request, context));
    }
}

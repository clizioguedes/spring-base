package com.dell.base.utils.validators;

import java.util.Set;

import org.springframework.http.HttpStatus;

import com.dell.base.utils.exception.BusinessException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * Utility class for validating entities using Bean Validation.
 * This class provides a static method to validate objects based on the defined
 * constraints.
 */
public class GenericEntityValidator {

    /**
     * Private constructor to prevent direct instantiation of the utility class.
     * This class should be used via its static methods.
     */
    private GenericEntityValidator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Validates a generic object using Bean Validation.
     *
     * @param object The object to be validated.
     * @param <T>    The type of the object to be validated.
     * @throws BusinessException If validation fails, containing error messages and
     *                           HTTP status.
     */
    public static <T> void validate(T object) {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Erro de validação: ");
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append(" ").append(violation.getMessage());
            }
            throw new BusinessException(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}

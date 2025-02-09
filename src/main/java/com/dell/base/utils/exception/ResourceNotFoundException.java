package com.dell.base.utils.exception;

/**
 * Exception thrown when a requested resource is not found.
 * It extends RuntimeException to indicate an unchecked exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

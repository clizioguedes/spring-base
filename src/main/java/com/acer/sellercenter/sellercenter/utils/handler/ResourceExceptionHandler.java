package com.acer.sellercenter.sellercenter.utils.handler;


import com.acer.sellercenter.sellercenter.utils.exception.ErrorDTO;
import com.acer.sellercenter.sellercenter.utils.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> notFound(ResourceNotFoundException exception, HttpServletRequest request) {

        var err = new ErrorDTO(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


}

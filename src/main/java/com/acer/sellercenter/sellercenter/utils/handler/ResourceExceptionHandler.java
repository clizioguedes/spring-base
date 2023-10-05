package com.acer.sellercenter.sellercenter.utils.handler;

import com.acer.sellercenter.sellercenter.utils.exception.BusinessException;
import com.acer.sellercenter.sellercenter.utils.exception.ErrorDTO;
import com.acer.sellercenter.sellercenter.utils.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> businessException(BusinessException exception, HttpServletRequest request) {

        var err = new ErrorDTO(
                ZonedDateTime.now(),
                exception.getHttpStatusCode().value(),
                "Business error",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> notFound(ResourceNotFoundException exception, HttpServletRequest request) {

        var err = new ErrorDTO(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> internalErrorException(Exception e, HttpServletRequest request) {

        var err = new ErrorDTO(
                ZonedDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected problem occurred.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }


}

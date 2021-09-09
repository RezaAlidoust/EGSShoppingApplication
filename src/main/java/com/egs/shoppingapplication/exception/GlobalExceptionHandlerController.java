package com.egs.shoppingapplication.exception;

import com.egs.shoppingapplication.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleCustomException(CustomException ex, WebRequest request) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorMessage(ex.getHttpStatus(), ex.getMessage() + ", details: " + ex.getDescription(), request.getDescription(true)));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(true)));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(HttpStatus.FORBIDDEN, "Access denied", request.getDescription(true)));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.BAD_REQUEST, "Something went wrong!", request.getDescription(true)));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, errors.toString(), request.getDescription(true)));
    }

}


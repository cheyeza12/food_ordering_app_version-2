package com.jumpstart.food_ordering_system.exception;

import com.jumpstart.food_ordering_system.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler catches exceptions thrown anywhere in the application
 * and returns clean JSON error responses instead of raw stack traces.
 *
 * Errors now use the same Response<T> wrapper as successful responses,
 * so the client always sees a consistent shape: statusCode, message, data, timestamp.
 *
 * @RestControllerAdvice applies this handler across all controllers globally.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles 404 - when a category id does not exist
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Response<Void>> handleCategoryNotFoundException(
            CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    // Handles 400 - when request body fails validation (@NotBlank, @Size)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // error() doesn't carry a data payload, but here we want the field-level
        // errors visible to the client, so we build the Response directly.
        Response<Map<String, String>> response = Response.<Map<String, String>>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .data(fieldErrors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
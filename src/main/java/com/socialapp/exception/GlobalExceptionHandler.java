package com.socialapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle our custom RuntimeExceptions
    // e.g "Post not found", "Email already in use"
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException ex) {

        // Determine status code based on message
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex.getMessage().contains("not found")) {
            status = HttpStatus.NOT_FOUND;             // 404
        } else if (ex.getMessage().contains("already")) {
            status = HttpStatus.CONFLICT;              // 409
        } else if (ex.getMessage().contains("only") ||
                ex.getMessage().contains("cannot")) {
            status = HttpStatus.FORBIDDEN;             // 403
        }

        ErrorResponse error = new ErrorResponse(
                status.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(status).body(error);
    }

    // Handle validation errors
    // e.g @NotBlank, @Email, @Size failures
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // Collect all field validation errors
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    // Handle any unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong. Please try again."
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}

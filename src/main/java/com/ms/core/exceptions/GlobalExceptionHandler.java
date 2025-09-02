package com.ms.core.exceptions;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ms.core.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @Autowired
  private Environment environment;

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.error(ex.getMessage(), getStackTrace(ex), 400));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponse.error("Unexpected error", getStackTrace(ex), 500));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
    String errors = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.joining("; "));

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.error("Validation failed: " + errors, getStackTrace(ex), 400));
  }

  @ExceptionHandler(SlugAlreadyExistsException.class)
  public ResponseEntity<ApiResponse<Void>> handleSlugConflict(SlugAlreadyExistsException ex) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ApiResponse.error(ex.getMessage(), getStackTrace(ex), 409));
  }

  private String getStackTrace(Exception ex) {
    if (isDev()) {
      return Arrays.stream(ex.getStackTrace())
          .map(StackTraceElement::toString)
          .collect(Collectors.joining("\n"));
    }
    return null;
  }

  private boolean isDev() {
    return Arrays.asList(environment.getActiveProfiles()).contains("dev");
  }
}

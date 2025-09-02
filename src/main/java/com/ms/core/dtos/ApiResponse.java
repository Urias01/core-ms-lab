package com.ms.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
  private boolean succeeded;
  private T data;
  private int code;
  private String message;
  private String stackTrace;

  public static <T> ApiResponse<T> success(T data, int code) {
    ApiResponse<T> response = new ApiResponse<>();
    response.succeeded = true;
    response.data = data;
    response.code = code;
    return response;
  }

  public static <T> ApiResponse<T> error(String message, String stackTrace, int code) {
    ApiResponse<T> response = new ApiResponse<>();
    response.succeeded = false;
    response.code = code;
    response.message = message;
    response.stackTrace = stackTrace;
    return response;
  }
}

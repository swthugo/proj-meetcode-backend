package dev.hugosiu.meetCode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiResponseDTO<T> {
  private boolean error;
  private String message;
  private T data;

  public static <T> ApiResponseDTO<T> ok(T data) {
    return ApiResponseDTO.<T>builder()
            .error(false)
            .message("Success")
            .data(data)
            .build();
  }

  public static <T> ApiResponseDTO<T> error(String message) {
    return ApiResponseDTO.<T>builder()
            .error(true)
            .message(message)
            .build();
  }
}

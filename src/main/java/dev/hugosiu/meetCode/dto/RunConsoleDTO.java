package dev.hugosiu.meetCode.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RunConsoleDTO {

  private boolean success;

  private String message;
}

package dev.hugosiu.meetCode.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompilerConsoleDTO {

  private boolean isValid = false;

  private String message;

}

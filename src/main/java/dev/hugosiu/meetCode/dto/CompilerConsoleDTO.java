package dev.hugosiu.meetCode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CompilerConsoleDTO {

  private boolean isValid = false;

  private String message;

}

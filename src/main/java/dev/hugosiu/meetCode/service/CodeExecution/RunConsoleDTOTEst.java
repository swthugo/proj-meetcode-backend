package dev.hugosiu.meetCode.service.CodeExecution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunConsoleDTOTEst {
  private boolean isPass;
  private String message;
}

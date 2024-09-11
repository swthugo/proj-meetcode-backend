package dev.hugosiu.meetCode.service.CodeExecution;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestEnv {
  private String classes;
  private String testClasses;
  private String packageName;
}

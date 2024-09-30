package dev.hugosiu.meetCode.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestExecuteDTO {

  private Long userId;

  private Long problemId;

  private String solution;

  private String testScript;
}

package dev.hugosiu.meetCode.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestConsoleDTO {

  private boolean success;

  private String consoleMessage;

  private Long userId;

  private Long problemId;

  private String solution;

  private String testScript;
}

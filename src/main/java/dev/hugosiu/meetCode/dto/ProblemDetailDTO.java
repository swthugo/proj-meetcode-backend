package dev.hugosiu.meetCode.dto;

import dev.hugosiu.meetCode.model.enumType.Level;
import dev.hugosiu.meetCode.model.enumType.Progress;
import dev.hugosiu.meetCode.model.enumType.Result;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProblemDetailDTO {
  private Long id;
  private String title;
  private String description;
  private Level level;
  private Boolean visibility;
  private Progress progress;
  private List<SubmissionDTO> submissionList;
  private List<TestCaseDTO> testCaseList;

  @Data
  @Builder
  public static class SubmissionDTO {
    private Long id;
    private Result runResult;
    private String script;
    private String console;
    private LocalDateTime createAt;
  }

  @Data
  @Builder
  public static class TestCaseDTO {
    private Long id;
    private String testScript;
  }
}

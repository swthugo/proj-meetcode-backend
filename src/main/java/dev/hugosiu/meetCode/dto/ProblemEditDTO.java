package dev.hugosiu.meetCode.dto;

import dev.hugosiu.meetCode.model.enumType.Level;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProblemEditDTO {
  private Long id;
  private String title;
  private String description;
  private Level level;
  private Boolean visibility;
  private String placeholder;
  private SolutionDTO solution;
  private List<TestCaseDTO> testCaseList;

  @Data
  @Builder
  public static class SolutionDTO {
    private Long id;
    private String solution;
  }

  @Data
  @Builder
  public static class TestCaseDTO {
    private Long id;
    private String testScript;
  }
}

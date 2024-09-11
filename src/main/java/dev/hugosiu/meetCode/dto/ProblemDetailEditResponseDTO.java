package dev.hugosiu.meetCode.dto;

import dev.hugosiu.meetCode.model.enumType.Level;
import dev.hugosiu.meetCode.model.enumType.Result;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProblemDetailEditResponseDTO {
  private Long id;
  private String title;
  private String description;
  private Level level;
  private String placeholder;
  private List<SubmissionDTO> submissionList;

  @Data
  @Builder
  public static class SubmissionDTO {
    private Long id;
    private Result runResult;
    private String script;
    private LocalDateTime createAt;
  }
}

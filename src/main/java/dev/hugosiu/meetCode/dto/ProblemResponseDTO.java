package dev.hugosiu.meetCode.dto;

import dev.hugosiu.meetCode.model.enumType.Level;
import dev.hugosiu.meetCode.model.enumType.Progress;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemResponseDTO {

  private Long id;

  private String title;

  private Level level;

  private Progress progress;

  private Boolean visibility;
}

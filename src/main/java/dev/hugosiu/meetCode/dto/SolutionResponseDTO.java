package dev.hugosiu.meetCode.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolutionResponseDTO {

  @NotBlank
  private String solution;
}

package dev.hugosiu.meetCode.dto;

import dev.hugosiu.meetCode.model.enumType.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProblemRequestDTO {

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotBlank
  private Level level;

  @NotBlank
  private Boolean visibility;

  @NotBlank
  private String placeholder;

  @NotBlank
  private String solution;

  @NotBlank
  private String testScript;

}

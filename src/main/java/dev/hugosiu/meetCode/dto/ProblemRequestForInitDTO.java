package dev.hugosiu.meetCode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemRequestForInitDTO {

  private String title;

  private String description;

  private String level;

  private Boolean visibility;

  private String placeholder;

  private String solution;

  private String testScript;
}

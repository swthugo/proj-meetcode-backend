package dev.hugosiu.meetCode.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequestDTO {

  @NotBlank
  private String displayName;

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  private String loginCode;
}

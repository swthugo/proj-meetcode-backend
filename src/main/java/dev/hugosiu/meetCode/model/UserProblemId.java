package dev.hugosiu.meetCode.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProblemId implements Serializable {
  @Serial
  private static final long serialVersionUID = -3287715633608041039L;

  private Long problemId;
  private Long userId;
}

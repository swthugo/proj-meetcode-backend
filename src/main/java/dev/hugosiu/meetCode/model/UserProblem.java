package dev.hugosiu.meetCode.model;

import dev.hugosiu.meetCode.model.enumType.Progress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_problem")
public class UserProblem {
  @Id
  private UserProblemId id;

  @ManyToOne
  @JoinColumn(name = "problem_id", nullable = false)
  @MapsId("problemId")
  private Problem problem;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @MapsId("userId")
  private User user;

  @NotNull
  @Column(nullable = false)
  private Progress progress;

  @CreatedDate
  private LocalDateTime createAt;
}

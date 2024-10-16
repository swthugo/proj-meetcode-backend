package dev.hugosiu.meetCode.model;

import dev.hugosiu.meetCode.model.enumType.Result;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "submission")
public class Submission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "problem_id", nullable = false)
  private Problem problem;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Result result;

  @NotBlank
  @Column(nullable = false, length = 2550)
  private String script;

  @NotBlank
  @Column(nullable = false, length = 25500)
  private String console;

  @CreatedDate
  @Column(name = "create_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createAt;

}

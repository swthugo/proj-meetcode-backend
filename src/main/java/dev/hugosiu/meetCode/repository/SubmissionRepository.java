package dev.hugosiu.meetCode.repository;

import dev.hugosiu.meetCode.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

  @Query("SELECT s FROM Submission s WHERE s.id = :id ")
  Optional<Submission> findById(@Param("id") Long id);

  @Query("SELECT s FROM Submission s " +
          "INNER JOIN User u ON u.id = s.user.id " +
          "WHERE u.uid= :uid AND s.problem.id = :problemId " +
          "ORDER BY s.id DESC")
  List<Submission> findSubmissionByUserIdAndProblemId(@Param("uid") String uid,
                                                      @Param(("problemId")) Long problemId);
}
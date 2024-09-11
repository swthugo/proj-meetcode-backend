package dev.hugosiu.meetCode.repository;

import dev.hugosiu.meetCode.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
  @Query("SELECT s FROM Solution s WHERE s.problem.id = :problemId")
  Solution findByProblemId(@Param("problemId") Long problemId);
}
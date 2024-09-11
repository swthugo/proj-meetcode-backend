package dev.hugosiu.meetCode.repository;

import dev.hugosiu.meetCode.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
  @Query("SELECT tc FROM TestCase tc WHERE tc.problem.id = :problemId")
  TestCase findByProblemId(@Param("problemId") Long problemId);
}
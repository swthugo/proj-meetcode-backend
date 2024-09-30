package dev.hugosiu.meetCode.repository;

import dev.hugosiu.meetCode.model.Problem;
import dev.hugosiu.meetCode.model.UserProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProblemRepository extends JpaRepository<UserProblem, Problem> {

  @Query("SELECT up FROM UserProblem up " +
          "INNER JOIN Problem p ON p.id = up.problem.id " +
          "WHERE up.user.id = :userId AND p.visibility = true ")
  List<UserProblem> findByUserId(@Param("userId") Long userId);

  @Query("SELECT up FROM UserProblem up " +
          "INNER JOIN User u ON u.id = up.user.id " +
          "WHERE u.uid = :uid AND up.problem.id = :problemId ")
  UserProblem findProblemByUidAndProblemId(@Param("uid") String uid, @Param("problemId") Long problemId);

  @Query("SELECT p FROM Problem p " +
          "LEFT JOIN UserProblem up ON p.id = up.problem.id AND up.user.id = :userId " +
          "WHERE up.problem IS NULL ")
  List<Problem> findMissingUserProblemByUserId(@Param("userId") Long userId);
}
package dev.hugosiu.meetCode.repository;

import dev.hugosiu.meetCode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u From User u WHERE u.uid = :uid")
  User findByUid(@Param("uid") String uid);
}
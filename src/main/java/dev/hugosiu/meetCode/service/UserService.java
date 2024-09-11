package dev.hugosiu.meetCode.service;

import dev.hugosiu.meetCode.exception.ResourceNotFoundException;
import dev.hugosiu.meetCode.model.User;
import dev.hugosiu.meetCode.model.enumType.Role;
import dev.hugosiu.meetCode.repository.ProblemRepository;
import dev.hugosiu.meetCode.repository.UserProblemRepository;
import dev.hugosiu.meetCode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProblemRepository problemRepository;

  @Autowired
  private UserProblemRepository userProblemRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public User findByUid(String uid) throws ResourceNotFoundException {
    User user = userRepository.findByUid(uid);

    if (user == null) {
      user = User.builder().uid(uid).role(Role.USER).build();
    }

    return user;
  }
}

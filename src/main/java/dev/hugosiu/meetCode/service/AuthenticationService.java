package dev.hugosiu.meetCode.service;

import dev.hugosiu.meetCode.exception.ResourceNotFoundException;
import dev.hugosiu.meetCode.model.User;
import dev.hugosiu.meetCode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired
  private UserRepository userRepository;

  public String getFirebaseUID() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println(authentication.getName());
    return authentication.getName();
  }

  public Long getUid() throws ResourceNotFoundException {
    String uid = getFirebaseUID();
    User user = userRepository.findByUid(uid);
    System.out.println(user.getId());
    return user.getId();
  }
}

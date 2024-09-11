package dev.hugosiu.meetCode.controller.auth;

import com.google.firebase.auth.FirebaseAuthException;
import dev.hugosiu.meetCode.dto.SignUpRequestDTO;
import dev.hugosiu.meetCode.dto.SignUpResponseDTO;
import dev.hugosiu.meetCode.exception.UnauthorizedOperationException;
import dev.hugosiu.meetCode.firebase.FireBaseAuthService;
import dev.hugosiu.meetCode.model.User;
import dev.hugosiu.meetCode.model.enumType.Role;
import dev.hugosiu.meetCode.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register")
public class SignUpController {
  @Autowired
  private FireBaseAuthService fireBaseAuthService;

  @Autowired
  private UserService userService;

  @Value("${admin.login.code}")
  private String adminLogInCode;

  @PostMapping("/user")
  public SignUpResponseDTO signUpUser(@Valid @RequestBody SignUpRequestDTO requestDTO) throws FirebaseAuthException {
    String firebaseUID = fireBaseAuthService.getFirebaseUID(requestDTO);
    fireBaseAuthService.setUserClaims(firebaseUID, List.of(Role.USER));

    userService.save(User.builder().uid(firebaseUID).role(Role.USER).build());

    return SignUpResponseDTO.builder()
            .uID(firebaseUID)
            .build();
  }

  @PostMapping("/admin")
  public SignUpResponseDTO signUpAdmin(@Valid @RequestBody SignUpRequestDTO requestDTO) throws UnauthorizedOperationException, FirebaseAuthException {
    if (!adminLogInCode.equals(requestDTO.getLoginCode())) {
      throw new UnauthorizedOperationException("Invalid admin secret code");
    }

    String firebaseUID = fireBaseAuthService.getFirebaseUID(requestDTO);
    fireBaseAuthService.setUserClaims(firebaseUID, List.of(Role.USER, Role.ADMIN));

    userService.save(User.builder().uid(firebaseUID).role(Role.ADMIN).build());

    return SignUpResponseDTO.builder()
            .uID(firebaseUID)
            .build();
  }
}

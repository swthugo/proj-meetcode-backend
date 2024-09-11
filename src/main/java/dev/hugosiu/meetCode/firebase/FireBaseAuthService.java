package dev.hugosiu.meetCode.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import dev.hugosiu.meetCode.dto.SignUpRequestDTO;
import dev.hugosiu.meetCode.model.enumType.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FireBaseAuthService {
  @Autowired
  private FirebaseAuth firebaseAuth;

  public String getFirebaseUID(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
    UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
            .setDisplayName(requestDTO.getDisplayName())
            .setEmail(requestDTO.getEmail())
            .setPassword(requestDTO.getPassword());

    UserRecord record = firebaseAuth.createUser(createRequest);
    String uid = record.getUid();
    String email = record.getEmail();
    log.info("[FirebaseAuthService] Created user [{}] for [{}]", uid, email);
    return uid;
  }

  public void setUserClaims(String uid, List<Role> requestPermissions) throws FirebaseAuthException {
    List<String> permissions = requestPermissions
            .stream().map(Enum::toString).toList();

    Map<String, Object> claims = Map.of("custom_claims", permissions);

    firebaseAuth.setCustomUserClaims(uid, claims);
  }
}

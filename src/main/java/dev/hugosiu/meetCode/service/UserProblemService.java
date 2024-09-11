package dev.hugosiu.meetCode.service;

import dev.hugosiu.meetCode.model.Problem;
import dev.hugosiu.meetCode.model.User;
import dev.hugosiu.meetCode.model.UserProblem;
import dev.hugosiu.meetCode.model.UserProblemId;
import dev.hugosiu.meetCode.model.enumType.Progress;
import dev.hugosiu.meetCode.repository.ProblemRepository;
import dev.hugosiu.meetCode.repository.UserProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProblemService {
  @Autowired
  private ProblemRepository problemRepository;

  @Autowired
  private UserProblemRepository userProblemRepository;

  public UserProblem save(UserProblem userProblem) {
    return userProblemRepository.save(userProblem);
  }

  public List<UserProblem> findByUserId(Long userId) {
    return userProblemRepository.findByUserId(userId);
  }

  public UserProblem findUserProblemByUidAndProblemId(String uid, Long problemId) {
    return userProblemRepository.findProblemByUidAndProblemId(uid, problemId);
  }

  public List<UserProblem> createUserProblem(User user) {
    List<Problem> problemList = problemRepository.findAll();
    return problemList.stream()
            .map((problem) ->
                    userProblemRepository.save(UserProblem.builder()
                            .id(new UserProblemId(user.getId(), problem.getId()))
                            .user(user)
                            .problem(problem)
                            .progress(Progress.NOT_STARTED)
                            .build()))
            .toList();
  }

  public List<Problem> findMissingUserProblemByUserId(Long userId) {
    return userProblemRepository.findMissingUserProblemByUserId(userId);
  }
}

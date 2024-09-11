package dev.hugosiu.meetCode.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hugosiu.meetCode.constant.SampleProblems;
import dev.hugosiu.meetCode.constant.UserConstant;
import dev.hugosiu.meetCode.dto.ProblemRequestForInitDTO;
import dev.hugosiu.meetCode.exception.ResourceNotFoundException;
import dev.hugosiu.meetCode.model.Problem;
import dev.hugosiu.meetCode.model.Solution;
import dev.hugosiu.meetCode.model.TestCase;
import dev.hugosiu.meetCode.model.User;
import dev.hugosiu.meetCode.model.enumType.Level;
import dev.hugosiu.meetCode.model.enumType.Role;
import dev.hugosiu.meetCode.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class InitializationService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProblemRepository problemRepository;

  @Autowired
  private SubmissionRepository submissionRepository;

  @Autowired
  private TestCaseRepository testCaseRepository;

  @Autowired
  private SolutionRepository solutionRepository;

  @PostConstruct
  public void init() throws ResourceNotFoundException {
    try {
      User admin = createAdminUser();
      initProblem(admin);
    } catch (ResourceNotFoundException e) {
      System.err.println("Initialization error: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
    }
  }

  private User createAdminUser() throws ResourceNotFoundException {
    if (!userRepository.existsById(UserConstant.SHARED_ADMIN_ID)) {
      User admin = User.builder()
              .uid(UserConstant.ADMIN_UID)
              .role(Role.ADMIN)
              .build();
      userRepository.save(admin);
    }

    return userRepository.findById(UserConstant.SHARED_ADMIN_ID)
            .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));
  }

  private void initProblem(User user) throws ResourceNotFoundException, IOException {
    for (ProblemRequestForInitDTO p : SampleProblems.PROBLEMS) {

      Problem problem = Problem.builder()
              .title(p.getTitle())
              .description(p.getDescription())
              .placeholder(p.getPlaceholder())
              .visibility(p.getVisibility())
              .level(Level.of(p.getLevel()))
              .build();
      problem = problemRepository.save(problem);
      Solution solution = Solution.builder()
              .problem(problem)
              .answer(p.getSolution())
              .build();
      solution = solutionRepository.save(solution);
      TestCase testCase = TestCase.builder()
              .problem(problem)
              .testScript(p.getTestScript())
              .build();
      testCase = testCaseRepository.save(testCase);

    }
  }
}

package dev.hugosiu.meetCode.controller.user;

import dev.hugosiu.meetCode.dto.*;
import dev.hugosiu.meetCode.dto.mapper.ProblemMapper;
import dev.hugosiu.meetCode.dto.mapper.TestConsoleMapper;
import dev.hugosiu.meetCode.exception.ResourceNotFoundException;
import dev.hugosiu.meetCode.model.*;
import dev.hugosiu.meetCode.model.enumType.Progress;
import dev.hugosiu.meetCode.service.*;
import dev.hugosiu.meetCode.service.CodeExecution.CodeExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/problem")
public class ProblemController {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserProblemService userProblemService;

  @Autowired
  private SubmissionService submissionService;

  @Autowired
  private SolutionService solutionService;

  @Autowired
  private TestCaseService testCaseService;

  @Autowired
  private CodeExecutionService codeExecutionService;

  @GetMapping
  public List<ProblemResponseDTO> getAllProblem() throws ResourceNotFoundException {

    String uid = authenticationService.getFirebaseUID();
    User user = userService.findByUid(uid);

    if (user.getId() == null) {
      User newUser = userService.save(user);
      userProblemService.createUserProblem(newUser);
    }

    List<Problem> missingUserProblems = userProblemService.findMissingUserProblemByUserId(user.getId());

    for (Problem problem : missingUserProblems) {
      System.err.println("run missingUserProblems");
      UserProblem userProblem = UserProblem.builder()
              .id(new UserProblemId(user.getId(), problem.getId()))
              .user(user)
              .problem(problem)
              .progress(Progress.NOT_STARTED)
              .build();
      userProblemService.save(userProblem);
    }

    List<UserProblem> userProblemList = userProblemService.findByUserId(user.getId());

    return userProblemList.stream()
            .map(userProblem -> ProblemMapper
                    .toResponseDTO(userProblem.getProblem(), userProblem.getProgress()))
            .toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProblemDetailEditResponseDTO> getProblem(@PathVariable(name = "id") Long problemId) throws ResourceNotFoundException {

    String uid = authenticationService.getFirebaseUID();
    UserProblem userProblem = userProblemService.findUserProblemByUidAndProblemId(uid, problemId);

    if (userProblem == null) {
      return ResponseEntity.notFound().build();
    }

    if (userProblem.getProgress() == Progress.NOT_STARTED) {
      userProblem.setProgress(Progress.OPEN);
      userProblemService.save(userProblem);
    }

    List<Submission> submissionList = submissionService.findSubmissionByUserIdAndProblemId(uid, problemId);

    List<ProblemDetailEditResponseDTO.SubmissionDTO> submissionDTO = submissionList.stream()
            .map(s -> ProblemDetailEditResponseDTO.SubmissionDTO.builder()
                    .id(s.getId())
                    .runResult(s.getResult())
                    .script(s.getScript())
                    .createAt(s.getCreateAt())
                    .build())
            .collect(Collectors.toList());

    Problem problem = userProblem.getProblem();
    ProblemDetailEditResponseDTO problemDetailEditResponseDTO = ProblemDetailEditResponseDTO.builder()
            .id(problem.getId())
            .title(problem.getTitle())
            .description(problem.getDescription())
            .level(problem.getLevel())
            .placeholder(problem.getPlaceholder())
            .submissionList(submissionDTO)
            .build();

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(problemDetailEditResponseDTO);
  }

  @GetMapping("/{id}/showAns")
  public ResponseEntity<Solution> getSolution(@PathVariable(name = "id") Long problemId) {
    Solution solution = solutionService.findByProblemId(problemId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(solution);
  }

  @GetMapping("/submit/{submitId}")
  public ResponseEntity<Submission> showPastSubmission(@PathVariable(name = "submitId") Long submitId) throws ResourceNotFoundException {
    Optional<Submission> submission = submissionService.findById(submitId);

    return submission.isPresent()
            ? ResponseEntity.status(HttpStatus.CREATED).body(submission.get())
            : ResponseEntity.notFound().build();
  }

  @PostMapping("/{id}/run")
  public ResponseEntity<RunConsoleDTO> runProblem(@PathVariable(name = "id") Long problemId,
                                                  @RequestBody SubmissionRequestDTO requestDTO)
          throws IOException, ResourceNotFoundException {

    Long userId = authenticationService.getUid();
    TestCase testCase = testCaseService.findByProblemId(problemId);

    RunConsoleDTO runConsoleDTO = CodeExecutionService.executeCode(
            userId, problemId, requestDTO.getSolution(), testCase.getTestScript()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(runConsoleDTO);
  }

  @PostMapping("/{id}/submit")
  public ResponseEntity<ProblemDetailDTO> submitProblem(@PathVariable(name = "id") Long problemId,
                                                        @RequestBody SubmissionRequestDTO requestDTO)
          throws IOException, ResourceNotFoundException {

    String uid = authenticationService.getFirebaseUID();

    UserProblem userProblem = userProblemService.findUserProblemByUidAndProblemId(uid, problemId);
    User user = userProblem.getUser();
    Problem problem = userProblem.getProblem();
    TestCase testCase = testCaseService.findByProblemId(problemId);

    RunConsoleDTO runConsoleDTO = CodeExecutionService.executeCode(
            userProblem.getUser().getId(),
            problemId,
            requestDTO.getSolution(),
            testCase.getTestScript()
    );

    Submission submission = TestConsoleMapper.toSubmissionEntity(user, problem, requestDTO, runConsoleDTO);
    Submission savedSubmission = submissionService.save(submission);

    if (runConsoleDTO.isSuccess() || userProblem.getProgress() == Progress.COMPLETED) {
      userProblem.setProgress(Progress.COMPLETED);
    } else {
      userProblem.setProgress(Progress.ATTEMPTING);
    }

    List<Submission> submissionList = submissionService.findSubmissionByUserIdAndProblemId(uid, problemId);
    UserProblem savedUserProblem = userProblemService.save(userProblem);

    ProblemDetailDTO problemDetailDTO = TestConsoleMapper
            .toResponseDTO(userProblem.getProblem(), submissionList, testCase, savedUserProblem);

    return ResponseEntity.status(HttpStatus.CREATED).body(problemDetailDTO);
  }
}

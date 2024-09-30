package dev.hugosiu.meetCode.controller.admin;

import dev.hugosiu.meetCode.constant.UserConstant;
import dev.hugosiu.meetCode.dto.*;
import dev.hugosiu.meetCode.dto.mapper.ProblemMapper;
import dev.hugosiu.meetCode.dto.mapper.TestConsoleMapper;
import dev.hugosiu.meetCode.exception.CompilerFailException;
import dev.hugosiu.meetCode.exception.ResourceNotFoundException;
import dev.hugosiu.meetCode.model.Problem;
import dev.hugosiu.meetCode.model.Solution;
import dev.hugosiu.meetCode.model.TestCase;
import dev.hugosiu.meetCode.model.enumType.Progress;
import dev.hugosiu.meetCode.service.CodeExecution.CodeExecutionService;
import dev.hugosiu.meetCode.service.ProblemService;
import dev.hugosiu.meetCode.service.SolutionService;
import dev.hugosiu.meetCode.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminProblemController {

  @Autowired
  private ProblemService problemService;

  @Autowired
  private SolutionService solutionService;

  @Autowired
  private TestCaseService testCaseService;

  @GetMapping("/dashboard")
  public List<ProblemResponseDTO> getAllProblem() {
    List<Problem> problemList = problemService.findAll();
    return problemList.stream()
            .map(problem -> ProblemMapper.toResponseDTO(problem, Progress.COMPLETED))
            .toList();
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping("/edit/new/run")
  public RunConsoleDTO checkProblem(@RequestBody ProblemRequestDTO requestDTO)
          throws IOException, ResourceNotFoundException, CompilerFailException {

    return CodeExecutionService.executeCode(
            UserConstant.SHARED_ADMIN_ID,
            UserConstant.SHARED_ADMIN_ID,
            requestDTO.getSolution(),
            requestDTO.getTestScript()
    );
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping("/edit/new/submit")
  public ResponseEntity<ProblemCreatedDTO> submitProblem(@RequestBody ProblemRequestDTO requestDTO)
          throws IOException, ResourceNotFoundException, CompilerFailException {

//    String uid = authenticationService.getFirebaseUID();
//    User admin = userService.findByUid(uid);
//    admin.setRole(Role.ADMIN);

    Problem problem = Problem.builder()
            .title(requestDTO.getTitle())
            .description(requestDTO.getDescription())
            .level(requestDTO.getLevel())
            .visibility(requestDTO.getVisibility())
            .placeholder(requestDTO.getPlaceholder())
            .build();

    RunConsoleDTO runConsoleDTO = CodeExecutionService.executeCode(
            UserConstant.SHARED_ADMIN_ID,
            UserConstant.SHARED_ADMIN_ID,
            requestDTO.getSolution(),
            requestDTO.getTestScript()
    );

    problem.setVisibility(runConsoleDTO.isSuccess());
    Problem savedProblem = problemService.save(problem);

    Solution solution = TestConsoleMapper.toSolutionEntity(savedProblem, requestDTO.getSolution());
    solution = solutionService.save(solution);

    TestCase testCase = TestConsoleMapper.toTestCaseEntity(savedProblem, requestDTO.getTestScript());
    testCase = testCaseService.save(testCase);

    ProblemCreatedDTO problemCreatedDTO = TestConsoleMapper
            .toProblemCreatedResponseDTO(
                    savedProblem,
                    solution,
                    testCase,
                    runConsoleDTO);

    return runConsoleDTO.isSuccess()
            ? ResponseEntity.status(HttpStatus.CREATED).body(problemCreatedDTO)
            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemCreatedDTO);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/edit/{id}")
  public ResponseEntity<ProblemEditDTO> getProblem(@PathVariable("id") Long id) throws ResourceNotFoundException {

    Problem problem = problemService.findById(id);
    if (problem == null) {
      return ResponseEntity.notFound().build();
    }

    Solution solution = solutionService.findByProblemId(problem.getId());
    TestCase testCase = testCaseService.findByProblemId(problem.getId());

    ProblemEditDTO problemEditDTO = TestConsoleMapper
            .toProblemCreatedResponseDTO(problem, solution, testCase);

    return ResponseEntity.status(HttpStatus.CREATED).body(problemEditDTO);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/edit/{id}/run")
  public ResponseEntity<TestConsoleDTO> runTestProblem(@PathVariable("id") Long id,
                                                       @RequestBody ProblemRequestDTO requestDTO)
          throws ResourceNotFoundException, IOException {

    RunConsoleDTO runConsoleDTO = CodeExecutionService.executeCode(
            UserConstant.SHARED_ADMIN_ID,
            UserConstant.SHARED_ADMIN_ID,
            requestDTO.getSolution(),
            requestDTO.getTestScript()
    );

    TestConsoleDTO testConsoleDTO = TestConsoleDTO.builder()
            .problemId(id)
            .solution(requestDTO.getSolution())
            .testScript(requestDTO.getTestScript())
            .success(runConsoleDTO.isSuccess())
            .consoleMessage(runConsoleDTO.getMessage())
            .build();

    return ResponseEntity.status(HttpStatus.CREATED).body(testConsoleDTO);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/edit/{id}")
  public ResponseEntity<ProblemCreatedDTO> updateProblem(@PathVariable("id") Long id,
                                                         @RequestBody ProblemRequestDTO requestDTO)
          throws ResourceNotFoundException, IOException {

    Problem problem = problemService.findById(id);
    if (problem == null) {
      return ResponseEntity.notFound().build();
    }

    Solution solution = solutionService.findByProblemId(problem.getId());
    TestCase testCase = testCaseService.findByProblemId(problem.getId());

    problem.setTitle(requestDTO.getTitle());
    problem.setDescription(requestDTO.getDescription());
    problem.setLevel(requestDTO.getLevel());
    problem.setPlaceholder(requestDTO.getPlaceholder());
    problem.setVisibility(requestDTO.getVisibility());

    solution.setAnswer(requestDTO.getSolution());
    testCase.setTestScript(requestDTO.getTestScript());

    RunConsoleDTO runConsoleDTO = CodeExecutionService.executeCode(
            UserConstant.SHARED_ADMIN_ID,
            UserConstant.SHARED_ADMIN_ID,
            requestDTO.getSolution(),
            requestDTO.getTestScript()
    );

    if (requestDTO.getVisibility()) {
      problem.setVisibility(runConsoleDTO.isSuccess());
    }

    problem = problemService.save(problem);
    solution = solutionService.save(solution);
    testCase = testCaseService.save(testCase);

    ProblemCreatedDTO problemCreatedDTO = TestConsoleMapper
            .toProblemCreatedResponseDTO(problem, solution, testCase, runConsoleDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(problemCreatedDTO);
  }
}

package dev.hugosiu.meetCode.dto.mapper;

import dev.hugosiu.meetCode.dto.*;
import dev.hugosiu.meetCode.model.*;
import dev.hugosiu.meetCode.model.enumType.Progress;
import dev.hugosiu.meetCode.model.enumType.Result;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestConsoleMapper {

  public static ProblemDetailDTO toResponseDTO(Problem problem,
                                               List<Submission> submissionlist,
                                               TestCase testCase,
                                               UserProblem userProblem) {

    List<ProblemDetailDTO.SubmissionDTO> submissionDTOS = submissionlist.stream()
            .map(s -> ProblemDetailDTO.SubmissionDTO.builder()
                    .id(s.getId())
                    .script(s.getScript())
                    .runResult(s.getResult())
                    .console(s.getConsole())
                    .createAt(s.getCreateAt())
                    .build()).collect(Collectors.toList());

    ProblemDetailDTO.TestCaseDTO testCaseDTO = ProblemDetailDTO.TestCaseDTO.builder()
            .id(testCase.getId())
            .testScript(testCase.getTestScript())
            .build();

    return ProblemDetailDTO.builder()
            .id(problem.getId())
            .title(problem.getTitle())
            .description(problem.getDescription())
            .level(problem.getLevel())
            .visibility(problem.getVisibility())
            .progress(userProblem.getProgress())
            .submissionList(submissionDTOS)
            .testCaseList(List.of(testCaseDTO))
            .build();
  }


  public static ProblemCreatedDTO toProblemCreatedResponseDTO(Problem problem,
                                                              Solution solution,
                                                              TestCase testCase,
                                                              RunConsoleDTO runConsoleDTO) {

    ProblemCreatedDTO.SolutionDTO solutionDTO = ProblemCreatedDTO.SolutionDTO.builder()
            .id(solution.getId())
            .solution(solution.getAnswer())
            .build();

    ProblemCreatedDTO.TestCaseDTO testCaseDTO = ProblemCreatedDTO.TestCaseDTO.builder()
            .id(testCase.getId())
            .testScript(testCase.getTestScript())
            .build();

    return ProblemCreatedDTO.builder()
            .id(problem.getId())
            .title(problem.getTitle())
            .description(problem.getDescription())
            .level(problem.getLevel())
            .visibility(problem.getVisibility())
            .placeholder(problem.getPlaceholder())
            .isSuccess(runConsoleDTO.isSuccess())
            .resultMessage(runConsoleDTO.getMessage())
            .solution(solutionDTO)
            .testCaseList(List.of(testCaseDTO))
            .build();

  }

  public static ProblemEditDTO toProblemCreatedResponseDTO(Problem problem,
                                                           Solution solution,
                                                           TestCase testCase) {

    ProblemEditDTO.SolutionDTO solutionDTO = ProblemEditDTO.SolutionDTO.builder()
            .id(solution.getId())
            .solution(solution.getAnswer())
            .build();

    ProblemEditDTO.TestCaseDTO testCaseDTO = ProblemEditDTO.TestCaseDTO.builder()
            .id(testCase.getId())
            .testScript(testCase.getTestScript())
            .build();

    return ProblemEditDTO.builder()
            .id(problem.getId())
            .title(problem.getTitle())
            .description(problem.getDescription())
            .level(problem.getLevel())
            .visibility(problem.getVisibility())
            .placeholder(problem.getPlaceholder())
            .solution(solutionDTO)
            .testCaseList(List.of(testCaseDTO))
            .build();
  }

  public static Submission toSubmissionEntity(User user,
                                              Problem problem,
                                              SubmissionRequestDTO requestDTO,
                                              RunConsoleDTO consoleDTO) {
    return Submission.builder()
            .problem(problem)
            .user(user)
            .result(consoleDTO.isSuccess() ? Result.PASS : Result.FAIL)
            .script(requestDTO.getSolution())
            .console(consoleDTO.getMessage())
            .createAt(LocalDateTime.now())
            .build();
  }

  public static Solution toSolutionEntity(Problem problem, String answer) {
    return Solution.builder()
            .problem(problem)
            .answer(answer)
            .build();
  }

  public static TestCase toTestCaseEntity(Problem problem,
                                          String testScript) {
    return TestCase.builder()
            .problem(problem)
            .testScript(testScript)
            .build();
  }

  public static UserProblem toUserProblemEntity(User user,
                                                Problem problem,
                                                TestConsoleDTO consoleDTO) {
    Progress progress = consoleDTO.isSuccess()
            ? Progress.COMPLETED
            : Progress.ATTEMPTING;

    return UserProblem.builder()
            .id(new UserProblemId(user.getId(), problem.getId()))
            .user(user)
            .problem(problem)
            .progress(progress)
            .build();
  }
}

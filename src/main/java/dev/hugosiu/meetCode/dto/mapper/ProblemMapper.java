package dev.hugosiu.meetCode.dto.mapper;

import dev.hugosiu.meetCode.dto.ProblemDetailEditResponseDTO;
import dev.hugosiu.meetCode.dto.ProblemResponseDTO;
import dev.hugosiu.meetCode.model.Problem;
import dev.hugosiu.meetCode.model.Submission;
import dev.hugosiu.meetCode.model.enumType.Progress;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProblemMapper {

  public static ProblemResponseDTO toResponseDTO(Problem problem, Progress progress) {
    if (problem == null) {
      return null;
    }

    return ProblemResponseDTO.builder()
            .id(problem.getId())
            .title(problem.getTitle())
            .level(problem.getLevel())
            .progress(progress)
            .visibility(problem.getVisibility())
            .build();
  }

  public static ProblemDetailEditResponseDTO toResponseDTOList(Problem problem,
                                                               List<Submission> submissionList) {

    List<ProblemDetailEditResponseDTO.SubmissionDTO> submissionDTOList = submissionList.stream()
            .map(s -> ProblemDetailEditResponseDTO.SubmissionDTO.builder()
                    .id(s.getId())
                    .runResult(s.getResult())
                    .script(s.getScript())
                    .createAt(s.getCreateAt())
                    .build())
            .toList();

    return ProblemDetailEditResponseDTO.builder()
            .id(problem.getId())
            .title(problem.getTitle())
            .description(problem.getDescription())
            .level(problem.getLevel())
            .placeholder(problem.getPlaceholder())
            .submissionList(submissionDTOList)
            .build();
  }
}

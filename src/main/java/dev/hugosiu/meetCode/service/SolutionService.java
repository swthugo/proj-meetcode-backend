package dev.hugosiu.meetCode.service;

import dev.hugosiu.meetCode.model.Solution;
import dev.hugosiu.meetCode.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService {
  @Autowired
  private SolutionRepository solutionRepository;

  public Solution save(Solution solution) {
    return solutionRepository.save(solution);
  }

  public Solution findByProblemId(Long problemId) {
    return solutionRepository.findByProblemId(problemId);
  }
}

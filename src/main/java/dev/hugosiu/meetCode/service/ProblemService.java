package dev.hugosiu.meetCode.service;

import dev.hugosiu.meetCode.exception.ResourceNotFoundException;
import dev.hugosiu.meetCode.model.Problem;
import dev.hugosiu.meetCode.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {
  @Autowired
  private ProblemRepository problemRepository;

  public Problem save(Problem problem) {
    return problemRepository.save(problem);
  }

  public List<Problem> findAll() {
    return problemRepository.findAll();
  }

  public Problem findById(Long id) throws ResourceNotFoundException {
    return problemRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Problem not Found By ID: " + id)
    );
  }
}

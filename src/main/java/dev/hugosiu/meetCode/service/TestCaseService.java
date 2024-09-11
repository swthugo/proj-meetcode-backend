package dev.hugosiu.meetCode.service;

import dev.hugosiu.meetCode.model.TestCase;
import dev.hugosiu.meetCode.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService {
  @Autowired
  private TestCaseRepository testCaseRepository;

  public TestCase save(TestCase testCase) {
    return testCaseRepository.save(testCase);
  }

  public TestCase findByProblemId(Long problemId) {
    return testCaseRepository.findByProblemId(problemId);
  }
}

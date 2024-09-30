package dev.hugosiu.meetCode.service;

import dev.hugosiu.meetCode.model.Submission;
import dev.hugosiu.meetCode.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
  @Autowired
  private SubmissionRepository submissionRepository;

  public Submission save(Submission submission) {
    return submissionRepository.save(submission);
  }

  public Optional<Submission> findById(Long id) {
    return submissionRepository.findById(id);
  }

  public List<Submission> findSubmissionByUserIdAndProblemId(String uid, Long problemId) {
    return submissionRepository.findSubmissionByUserIdAndProblemId(uid, problemId);
  }
}

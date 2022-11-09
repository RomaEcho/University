package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.ExamRepository;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.service.ExamService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamServiceImpl implements ExamService {
    static final String GET_EXAM_ERROR = "::Error while getting exam with id";
    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public void addExam(@NonNull Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void removeExam(@NonNull Exam exam) {
        examRepository.delete(exam);
    }

    @Override
    public Exam getExam(Long id) {
        return examRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_EXAM_ERROR, id)));
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public void editExam(@NonNull Exam exam) {
        examRepository.save(exam);
    }
}

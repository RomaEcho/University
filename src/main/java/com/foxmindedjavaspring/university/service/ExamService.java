package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Exam;

public interface ExamService {

    void addExam(Exam exam);

    void removeExam(Exam exam);

    Exam getExam(Long id);

    List<Exam> getAllExams();

    void editExam(Exam exam);
}
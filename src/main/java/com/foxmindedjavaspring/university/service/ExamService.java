package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Exam;

import java.util.List;

public interface ExamService {

    void addExam(Exam exam);

    void editExam(Exam exam);

    void removeExam(Exam exam);

    Exam getExam(Long id);

    List<Exam> getAllExams();
}
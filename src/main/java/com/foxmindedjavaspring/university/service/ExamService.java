package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Exam;

public interface ExamService {

    void addExam(Exam exam);

    void removeExam(long id);

    Exam getExam(long id);

    List<Exam> getAllExams();

}
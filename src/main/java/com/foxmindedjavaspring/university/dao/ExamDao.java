package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Exam;

public interface ExamDao {
    void addExam(Exam exam);

    void removeExam(Exam exam);
}

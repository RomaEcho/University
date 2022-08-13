package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Exam;

public interface ExamDao {
    boolean create(Exam exam);

    boolean delete(Exam exam);

    boolean addDescription(Exam exam, String description);
}

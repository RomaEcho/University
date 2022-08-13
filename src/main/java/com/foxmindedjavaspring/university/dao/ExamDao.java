package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Exam;

public interface ExamDao {
    void create(Exam exam);

    void delete(Exam exam);

    void addDescription(Exam exam, String description);
}

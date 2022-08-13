package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.model.Exam;

@Component
public class ExamDaoImpl implements ExamDao {
    public static final String ADD_EXAM = "INSERT INTO exams(title) VALUES(?)";
    public static final String REMOVE_EXAM = "DELETE FROM exams WHERE title = ?";
    public static final String ADD_DESCRIPTION = "UPDATE exams SET description = ? WHERE title = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExamDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Exam exam) {
        return jdbcTemplate.update(ADD_EXAM, exam.getTitle()) == 1;
    }

    @Override
    public boolean delete(Exam exam) {
        return jdbcTemplate.update(REMOVE_EXAM, exam.getTitle()) == 1;
    }

    @Override
    public boolean addDescription(Exam exam, String description) {
        return jdbcTemplate.update(ADD_DESCRIPTION, description, exam.getTitle()) == 1;
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.model.Exam;

@Component
public class ExamDaoImpl implements ExamDao {
    private static final String ADD_EXAM = "INSERT INTO exams(title) VALUES(?)";
    private static final String REMOVE_EXAM = "DELETE FROM exams WHERE title = ?";
    private static final String ADD_DESCRIPTION = "UPDATE exams SET description = ? WHERE title = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExamDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Exam exam) {
        jdbcTemplate.update(ADD_EXAM, exam.getTitle());
    }

    @Override
    public void delete(Exam exam) {
        jdbcTemplate.update(REMOVE_EXAM, exam.getTitle());
    }

    @Override
    public void addDescription(Exam exam, String description) {
        jdbcTemplate.update(ADD_DESCRIPTION, description, exam.getTitle());
    }
}

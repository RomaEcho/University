package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.SubjectDao;
import com.foxmindedjavaspring.university.model.Subject;

@Component
public class SubjectDaoImpl implements SubjectDao {
    private static final String ADD_SUBJECT = "INSERT INTO subjects VALUES(?, ?, ?)";
    private static final String REMOVE_SUBJECT = "DELETE FROM subjects WHERE number = ?";
    private static final String ADD_DESCRIPTION = "UPDATE subjects SET description = ? WHERE number = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubjectDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Subject subject) {
        jdbcTemplate.update(ADD_SUBJECT, subject.getNumber(),
                subject.getName(), subject.getDescription());
    }

    @Override
    public void delete(Subject subject) {
        jdbcTemplate.update(REMOVE_SUBJECT, subject.getNumber());
    }

    @Override
    public void addDescription(Subject subject, String description) {
        jdbcTemplate.update(ADD_DESCRIPTION, description,
                subject.getNumber());
    }
}

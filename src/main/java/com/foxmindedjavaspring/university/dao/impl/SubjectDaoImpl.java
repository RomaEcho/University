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
    public boolean create(Subject subject) {
        return jdbcTemplate.update(ADD_SUBJECT, subject.getNumber(),
                subject.getName(), subject.getDescription()) == 1;
    }

    @Override
    public boolean delete(Subject subject) {
        return jdbcTemplate.update(REMOVE_SUBJECT, subject.getNumber()) == 1;
    }

    @Override
    public boolean addDescription(Subject subject, String description) {
        return jdbcTemplate.update(ADD_DESCRIPTION, description,
                subject.getNumber()) == 1;
    }
}

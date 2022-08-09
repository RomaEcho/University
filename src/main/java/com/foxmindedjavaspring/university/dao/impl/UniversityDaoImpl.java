package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.UniversityDao;
import com.foxmindedjavaspring.university.model.University;

@Component
public class UniversityDaoImpl implements UniversityDao {
    private static final String ADD_UNIVERSITY = "INSERT INTO universities VALUES(?, ?)";
    private static final String REMOVE_UNIVERSITY = "DELETE FROM universities WHERE name = ? AND hq_location = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UniversityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUniversity(University university) {
        jdbcTemplate.update(ADD_UNIVERSITY, university.getName(),
                university.getHqLocation());
    }

    @Override
    public void removeUniversity(University university) {
        jdbcTemplate.update(REMOVE_UNIVERSITY, university.getName(),
                university.getHqLocation());
    }
}

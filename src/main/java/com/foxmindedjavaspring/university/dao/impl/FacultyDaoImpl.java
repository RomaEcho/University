package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.FacultyDao;
import com.foxmindedjavaspring.university.model.Faculty;

@Component
public class FacultyDaoImpl implements FacultyDao {
    private static final String ADD_FACULTY = "INSERT INTO faculties(university_id, department, adress) VALUES((SELECT id FROM universities WHERE name = ?), ?, ?)";
    private static final String REMOVE_FACULTY = "DELETE FROM faculties WHERE department = ? AND adress = ?";
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public FacultyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Faculty faculty) {
        return jdbcTemplate.update(ADD_FACULTY, faculty.getUniversity().getName(),
                faculty.getDepartment(), faculty.getAddress()) == 1;
    }

    @Override
    public boolean delete(Faculty faculty) {
        return jdbcTemplate.update(REMOVE_FACULTY, faculty.getDepartment(),
                faculty.getAddress()) == 1;
    }
}

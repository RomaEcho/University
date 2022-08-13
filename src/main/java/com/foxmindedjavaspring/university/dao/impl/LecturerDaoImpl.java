package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.LecturerDao;
import com.foxmindedjavaspring.university.model.Lecturer;

@Component
public class LecturerDaoImpl implements LecturerDao {
    private static final String ADD_LECTURER = "INSERT INTO lecturers(staff_id, level) VALUES(?, ?)";
    private static final String REMOVE_LECTURER = "DELETE FROM lecturers WHERE staff_id = ? AND level = ?";
    private static final String UPDATE_LEVEL = "UPDATE lecturers SET level = ? WHERE staff_id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LecturerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Lecturer lecturer) {
        return jdbcTemplate.update(ADD_LECTURER, lecturer.getStaffId(),
                lecturer.getLevel()) == 1;
    }

    @Override
    public boolean delete(Lecturer lecturer) {
        return jdbcTemplate.update(REMOVE_LECTURER, lecturer.getStaffId(),
                lecturer.getLevel()) == 1;
    }

    @Override
    public boolean updateLevel(Lecturer lecturer, String level) {
        return jdbcTemplate.update(UPDATE_LEVEL, level, lecturer.getStaffId()) == 1;
    }
}

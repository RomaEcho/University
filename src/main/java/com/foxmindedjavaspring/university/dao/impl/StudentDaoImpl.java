package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.StudentDao;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;

@Component
public class StudentDaoImpl implements StudentDao {
    private static final String ADD_STUDENT = "INSERT INTO students VALUES(?, ?, ?)";
    private static final String REMOVE_STUDENT = "DELETE FROM students WHERE staff_id = ?";
    private static final String SET_STATE = "UPDATE students SET state = ? WHERE staff_id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Student student) {
        return jdbcTemplate.update(ADD_STUDENT, student.getStaffId(),
                student.getStartDate(), student.getState()) == 1;
    }

    @Override
    public boolean delete(Student student) {
        return jdbcTemplate.update(REMOVE_STUDENT, student.getStaffId()) == 1;
    }

    @Override
    public boolean setState(Student student, StudentState state) {
        return jdbcTemplate.update(SET_STATE, state, student.getStaffId()) == 1;
    }
}

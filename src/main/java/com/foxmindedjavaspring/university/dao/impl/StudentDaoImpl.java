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
    public void addStudent(Student student) {
        jdbcTemplate.update(ADD_STUDENT, student.getStaffId(),
                student.getStartDate(), student.getState());
    }

    @Override
    public void removeStudent(Student student) {
        jdbcTemplate.update(REMOVE_STUDENT, student.getStaffId());
    }

    @Override
    public void setState(Student student, StudentState state) {
        jdbcTemplate.update(SET_STATE, state, student.getStaffId());
    }
}

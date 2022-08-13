package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.CourseDao;
import com.foxmindedjavaspring.university.model.Course;

@Component
public class CourseDaoImpl implements CourseDao {
    private final JdbcTemplate jdbcTemplate;
    private static final String ADD_COURSE = "INSERT INTO courses(topic, number_of_hours) VALUES(?, ?)";
    private static final String REMOVE_COURSE = "DELETE FROM courses WHERE topic = ? AND number_of_hours = ?";
    private static final String ADD_RATE = "UPDATE courses SET rate = ? WHERE topic = ? AND number_of_hours = ?";

    @Autowired
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Course course) {
        return jdbcTemplate.update(ADD_COURSE, course.getTopic(),
                course.getNumberOfHours()) == 1;
    }

    @Override
    public boolean delete(Course course) {
        return jdbcTemplate.update(REMOVE_COURSE, course.getTopic(),
                course.getNumberOfHours()) == 1;
    }

    @Override
    public boolean addRate(Course course, int rate) {
        return jdbcTemplate.update(ADD_RATE, rate, course.getTopic(),
                course.getNumberOfHours()) == 1;
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.Utils;
import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.model.Exam;

@Repository
public class ExamDaoImpl implements ExamDao {
    public static final String CREATE_EXAM = "INSERT INTO exams(title) VALUES(:title)";
    public static final String DELETE_EXAM = "DELETE FROM exams WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM exams WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM exams";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ExamDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(Exam exam) {
        return jdbcTemplate.update(CREATE_EXAM, 
                Utils.getSingleNamed("title", exam.getTitle()));
    }

    public int delete(long id) {
        return jdbcTemplate.update(DELETE_EXAM, 
                Utils.getSingleNamed("id", id));
    }

    public Exam findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, 
                Utils.getSingleNamed("id", id), 
                new BeanPropertyRowMapper<>(Exam.class));
    }

    public List<Exam> findAll() {
        return jdbcTemplate.query(FIND_ALL, 
                new BeanPropertyRowMapper<>(Exam.class));
    }
}

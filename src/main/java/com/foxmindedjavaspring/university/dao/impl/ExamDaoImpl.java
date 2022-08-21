package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class ExamDaoImpl implements ExamDao<Exam> {
    public static final String CREATE_EXAM = "INSERT INTO exams(title) VALUES(:title)";
    public static final String DELETE_EXAM = "DELETE FROM exams WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM exams WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM exams";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ExamDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(Exam exam) {
        return jdbcTemplate.update(CREATE_EXAM,
                Utils.getMapSinglePair("title", exam.getTitle()));
    }

    public int delete(long id) {
        return jdbcTemplate.update(DELETE_EXAM,
                Utils.getMapSinglePair("id", id));
    }

    public Exam findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                Utils.getMapSinglePair("id", id), new ExamMapper());
    }

    public List<Exam> findAll() {
        return jdbcTemplate.query(FIND_ALL, new ExamMapper());
    }

    class ExamMapper implements RowMapper<Exam> {
        @Override
        public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Exam(rs.getString("title"));
        }
    }
}

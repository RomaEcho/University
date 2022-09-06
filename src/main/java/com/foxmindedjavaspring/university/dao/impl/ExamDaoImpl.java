package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Exam;

@Repository
public class ExamDaoImpl implements GenericDao<Exam> {
    static final String CREATE_EXAM = "INSERT INTO exams(title) VALUES(:title)";
    static final String DELETE_EXAM_BY_ID = "DELETE FROM exams WHERE id = :id";
    static final String FIND_BY_ID = "SELECT * FROM exams WHERE id = :id";
    static final String FIND_ALL = "SELECT * FROM exams";
    static final String SQL_CREATE_EXAM_ERROR = " :: Error while creating the exam with title: {}";
    static final String SQL_DELETE_EXAM_BY_ID_ERROR = " :: Error while deleting the exam with id: {}";
    static final String SQL_FIND_EXAM_ERROR = " :: Error while searching the exam with id: {}";
    static final String SQL_FIND_ALL_EXAMS_ERROR = " :: Error while searching all exams.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ExamDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Exam exam) {
        try {
            return jdbcTemplate.update(CREATE_EXAM,
                    Collections.singletonMap("title", exam.getTitle()));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_CREATE_EXAM_ERROR,
                    exam.getTitle());
        }
    }

    @Override
    public int delete(Long id) {
        try {
            return jdbcTemplate.update(DELETE_EXAM_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_EXAM_BY_ID_ERROR,
                    id);
        }
    }

    @Override
    public Exam findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), new ExamMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_FIND_EXAM_ERROR,
                    id);
        }
    }

    @Override
    public List<Exam> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new ExamMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_ALL_EXAMS_ERROR);
        }
    }

    static class ExamMapper implements RowMapper<Exam> {
        @Override
        public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Exam(rs.getString("title"));
        }
    }
}

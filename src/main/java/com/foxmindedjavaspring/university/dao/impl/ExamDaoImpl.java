package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class ExamDaoImpl implements ExamDao<Exam> {
    public static final String CREATE_EXAM = "INSERT INTO exams(title) VALUES(:title)";
    public static final String DELETE_EXAM = "DELETE FROM exams WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM exams WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM exams";
    private static final String SQL_CREATE_EXAM_ERROR = " :: Error while creating the exam with title:";
    private static final String SQL_DELETE_EXAM_ERROR = " :: Error while deleting the exam with id:";
    private static final String SQL_FIND_EXAM_ERROR = " :: Error while searching the exam with id:";
    private static final String SQL_FIND_ALL_EXAMS_ERROR = " :: Error while searching all exams.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ExamDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Exam exam) {
        try {
	    return jdbcTemplate.update(CREATE_EXAM,
	            Utils.getMapSinglePair("title", exam.getTitle()));
	} catch (Exception e) {
        throw new UniversityDataAcessException(
            SQL_CREATE_EXAM_ERROR + exam.getTitle(), e);
	}
    }

    @Override
    public int delete(long id) {
        try {
	    return jdbcTemplate.update(DELETE_EXAM,
	            Utils.getMapSinglePair("id", id));
	} catch (Exception e) {
	    throw new UniversityDataAcessException(
                SQL_DELETE_EXAM_ERROR + id, e);
	}
    }

    @Override
    public Exam findById(long id) {
        try {
	    return jdbcTemplate.queryForObject(FIND_BY_ID,
	            Utils.getMapSinglePair("id", id), new ExamMapper());
	} catch (Exception e) {
	    throw new UniversityDataAcessException(
                    SQL_FIND_EXAM_ERROR + id, e);
	}
    }

    @Override
    public List<Exam> findAll() {
        try {
	    return jdbcTemplate.query(FIND_ALL, new ExamMapper());
	} catch (Exception e) {
	    throw new UniversityDataAcessException(
                    SQL_FIND_ALL_EXAMS_ERROR, e);
	}
    }

    class ExamMapper implements RowMapper<Exam> {
        @Override
        public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Exam(rs.getString("title"));
        }
    }
}

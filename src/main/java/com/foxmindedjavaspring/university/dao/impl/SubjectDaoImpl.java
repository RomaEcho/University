package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class SubjectDaoImpl implements GenericDao<Subject> {
    public static final String CREATE_SUBJECT = "INSERT INTO subjects VALUES(:number, :name, :description)";
    public static final String DELETE_SUBJECT = "DELETE FROM subjects WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM subjects WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM subjects";
    public static final String SQL_CREATE_SUBJECT_ERROR = " :: Error while creating the subject with number:";
    public static final String SQL_DELETE_SUBJECT_ERROR = " :: Error while deleting the subject with id:";
    public static final String SQL_FIND_SUBJECT_ERROR = " :: Error while searching the subject with id:";
    public static final String SQL_FIND_ALL_SUBJECTS_ERROR = " :: Error while searching all subjects.";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Utils utils;

    public SubjectDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, Utils utils) {
        this.jdbcTemplate = jdbcTemplate;
        this.utils = utils;
    }

    @Override
    public int create(Subject subject) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("number", subject.getNumber());
            namedParameters.put("name", subject.getName());
            namedParameters.put("description", subject.getDescription());
            return jdbcTemplate.update(CREATE_SUBJECT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_CREATE_SUBJECT_ERROR + subject.getNumber(), e);
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_SUBJECT,
                    utils.getMapSinglePair("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_DELETE_SUBJECT_ERROR + id, e);
        }
    }

    @Override
    public Subject findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    utils.getMapSinglePair("id", id), new SubjectMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_FIND_SUBJECT_ERROR + id, e);
        }
    }

    @Override
    public List<Subject> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new SubjectMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_FIND_ALL_SUBJECTS_ERROR, e);
        }
    }

    static class SubjectMapper implements RowMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Subject(rs.getInt("number"), rs.getString("name"));
        }
    }
}

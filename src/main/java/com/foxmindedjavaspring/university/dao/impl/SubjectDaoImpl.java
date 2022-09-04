package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Subject;

@Repository
public class SubjectDaoImpl implements GenericDao<Subject> {
    static final String CREATE_SUBJECT = "INSERT INTO subjects VALUES(:number, :name, :description)";
    static final String DELETE_SUBJECT_BY_ID = "DELETE FROM subjects WHERE id = :id";
    static final String DELETE_SUBJECT = "DELETE FROM subjects WHERE number = :number";
    static final String FIND_BY_ID = "SELECT * FROM subjects WHERE id = :id";
    static final String FIND_ALL = "SELECT * FROM subjects";
    static final String SQL_CREATE_SUBJECT_ERROR = " :: Error while creating the subject with number: {}";
    static final String SQL_DELETE_SUBJECT_BY_ID_ERROR = " :: Error while deleting the subject with id: {}";
    static final String SQL_DELETE_SUBJECT_ERROR = " :: Error while deleting the subject with number: {}, name: {}";
    static final String SQL_FIND_SUBJECT_ERROR = " :: Error while searching the subject with id: {}";
    static final String SQL_FIND_ALL_SUBJECTS_ERROR = " :: Error while searching all subjects.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SubjectDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Subject subject) {
        try {
            Map<String, Object> namedParameters = Map.of(
                    "number", subject.getNumber(),
                    "name", subject.getName(),
                    "description", subject.getDescription());
            return jdbcTemplate.update(CREATE_SUBJECT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_SUBJECT_ERROR, subject.getNumber());
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_SUBJECT_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_SUBJECT_BY_ID_ERROR, id);
        }
    }

    @Override
    public int delete(Subject subject) {
        try {
            return jdbcTemplate.update(DELETE_SUBJECT,
                    Collections.singletonMap("number", 
                    subject.getNumber()));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_SUBJECT_ERROR,
                    subject.getNumber(),
                    subject.getName());
        }
    }

    @Override
    public Subject findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), new SubjectMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_SUBJECT_ERROR, id);
        }
    }

    @Override
    public List<Subject> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new SubjectMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_ALL_SUBJECTS_ERROR);
        }
    }

    static class SubjectMapper implements RowMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Subject(rs.getInt("number"), rs.getString("name"));
        }
    }
}

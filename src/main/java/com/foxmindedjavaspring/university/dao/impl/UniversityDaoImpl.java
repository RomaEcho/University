package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.University;

@Repository
public class UniversityDaoImpl implements GenericDao<University> {
    static final String CREATE_UNIVERSITY = "INSERT INTO universities VALUES(:name, :hq_location)";
    static final String DELETE_UNIVERSITY_BY_ID = "DELETE FROM universities WHERE id = :id";
    static final String DELETE_UNIVERSITY = "DELETE FROM universities WHERE name = :name";
    static final String FIND_BY_ID = "SELECT * FROM universities WHERE id = :id";
    static final String FIND_ALL = "SELECT * FROM universities";
    static final String SQL_CREATE_UNIVERSITY_ERROR = " :: Error while creating the university with name: {}";
    static final String SQL_DELETE_UNIVERSITY_BY_ID_ERROR = " :: Error while deleting the university with id: {}";
    static final String SQL_DELETE_UNIVERSITY_ERROR = " :: Error while deleting the university with name: {}";
    static final String SQL_FIND_UNIVERSITY_ERROR = " :: Error while searching the university with id: {}";
    static final String SQL_FIND_ALL_UNIVERSITIES_ERROR = " :: Error while searching all universities.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UniversityDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(University university) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("name", university.getName());
            namedParameters.put("hq_location", 
                    university.getHqLocation());
            return jdbcTemplate.update(CREATE_UNIVERSITY, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_UNIVERSITY_ERROR, university.getName());
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_UNIVERSITY_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_UNIVERSITY_BY_ID_ERROR, id);
        }
    }

    @Override
    public int delete(University university) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("name", university.getName());
            namedParameters.put("hq_location", 
                    university.getHqLocation());
            return jdbcTemplate.update(DELETE_UNIVERSITY, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_UNIVERSITY_ERROR, university.getName());
        }
    }

    @Override
    public University findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), new UniversityMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_UNIVERSITY_ERROR, id);
        }
    }

    @Override
    public List<University> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new UniversityMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_ALL_UNIVERSITIES_ERROR);
        }
    }

    static class UniversityMapper implements RowMapper<University> {
        @Override
        public University mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            return new University(rs.getString("name"),
                    rs.getString("hq_location"));
        }
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static final String FIND_BY_ID = "SELECT * FROM universities WHERE id = :id";
    static final String FIND_ALL = "SELECT * FROM universities";
    static final String SQL_CREATE_UNIVERSITY_ERROR = " :: Error while creating the university with name: {}";
    static final String SQL_DELETE_UNIVERSITY_BY_ID_ERROR = " :: Error while deleting the university with id: {}";
    static final String SQL_FIND_UNIVERSITY_ERROR = " :: Error while searching the university with id: {}";
    static final String SQL_FIND_ALL_UNIVERSITIES_ERROR = " :: Error while searching all universities.";
    private static final String DEBUG_CREATE_UNIVERSITY = "Trying to create the university with name: {} using the following SQL: {}";
    private static final String DEBUG_DELETE_UNIVERSITY = "Trying to delete the university with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_UNIVERSITY = "Trying to find the university with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_ALL_UNIVERSITIES = "Trying to find all the universities using the following SQL: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(
                UniversityDaoImpl.class);

    public UniversityDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(University university) {
        try {
            LOG.debug(DEBUG_CREATE_UNIVERSITY, university.getName(), 
                    CREATE_UNIVERSITY);
            Map<String, Object> namedParameters = Map.of(
                    "name", university.getName(),
                    "hq_location", university.getHqLocation());
            return jdbcTemplate.update(CREATE_UNIVERSITY, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_UNIVERSITY_ERROR, university.getName());
        }
    }

    @Override
    public int delete(Long id) {
        try {
            LOG.debug(DEBUG_DELETE_UNIVERSITY, id, DELETE_UNIVERSITY_BY_ID);
            return jdbcTemplate.update(DELETE_UNIVERSITY_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_UNIVERSITY_BY_ID_ERROR, id);
        }
    }

    @Override
    public University findById(Long id) {
        try {
            LOG.debug(DEBUG_FIND_UNIVERSITY, id, FIND_BY_ID);
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
            LOG.debug(DEBUG_FIND_ALL_UNIVERSITIES, FIND_ALL);
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

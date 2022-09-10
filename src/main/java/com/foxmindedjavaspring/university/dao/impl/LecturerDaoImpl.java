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
import com.foxmindedjavaspring.university.model.Lecturer;

@Repository
public class LecturerDaoImpl implements GenericDao<Lecturer> {
    static final String CREATE_LECTURER = "INSERT INTO lecturers(staff_id, level) VALUES(:staff_id, :level)";
    static final String DELETE_LECTURER_BY_ID = "DELETE FROM lecturers WHERE id = :id";
    static final String FIND_BY_ID = "SELECT * FROM lecturers WHERE id = :id";
    static final String FIND_ALL = "SELECT * FROM lecturers";
    static final String SQL_CREATE_LECTURER_ERROR = " :: Error while creating the lecturer with staff_id: {}";
    static final String SQL_DELETE_LECTURER_BY_ID_ERROR = " :: Error while deleting the lecturer with id: {}";
    static final String SQL_FIND_LECTURER_ERROR = " :: Error while searching the lecturer with id: {}";
    static final String SQL_FIND_ALL_LECTURERS_ERROR = " :: Error while searching all lecturers.";
    private static final String DEBUG_CREATE_LECTURER = "Trying to create the lecturer with staff_id: {} using the following SQL: {}";
    private static final String DEBUG_DELETE_LECTURER = "Trying to delete the lecturer with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_LECTURER = "Trying to find the lecturer with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_ALL_LECTURERS = "Trying to find all the lecturers using the following SQL: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(
                LecturerDaoImpl.class);

    public LecturerDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Lecturer lecturer) {
        try {
            LOG.debug(DEBUG_CREATE_LECTURER, lecturer.getStaffId(), 
                    CREATE_LECTURER);
            Map<String, Object> namedParameters = Map.of(
                    "staff_id", lecturer.getStaffId(),
                    "level", lecturer.getLevel());
            return jdbcTemplate.update(CREATE_LECTURER, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_LECTURER_ERROR, lecturer.getStaffId());
        }
    }

    @Override
    public int delete(Long id) {
        try {
            LOG.debug(DEBUG_DELETE_LECTURER, id, DELETE_LECTURER_BY_ID);
            return jdbcTemplate.update(DELETE_LECTURER_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_LECTURER_BY_ID_ERROR, id);
        }
    }

    @Override
    public Lecturer findById(Long id) {
        try {
            LOG.debug(DEBUG_FIND_LECTURER, id, FIND_BY_ID);
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id),
                    new LecturerMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_LECTURER_ERROR, id);
        }
    }

    @Override
    public List<Lecturer> findAll() {
        try {
            LOG.debug(DEBUG_FIND_ALL_LECTURERS, FIND_ALL);
            return jdbcTemplate.query(FIND_ALL, new LecturerMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_ALL_LECTURERS_ERROR);
        }
    }

    static class LecturerMapper implements RowMapper<Lecturer> {
        @Override
        public Lecturer mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            return new Lecturer.Builder<>()
                    .withLevel(rs.getString("level"))
                    .withStaffId(rs.getLong("staff_id"))
                    .build();
        }
    }
}

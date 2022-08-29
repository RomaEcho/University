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
import com.foxmindedjavaspring.university.model.Lecturer;

@Repository
public class LecturerDaoImpl implements GenericDao<Lecturer> {
    public static final String CREATE_LECTURER = "INSERT INTO lecturers(staff_id, level) VALUES(:staff_id, :level)";
    public static final String DELETE_LECTURER = "DELETE FROM lecturers WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM lecturers WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM lecturers";
    public static final String SQL_CREATE_LECTURER_ERROR = " :: Error while creating the lecturer with staff_id: {}";
    public static final String SQL_DELETE_LECTURER_ERROR = " :: Error while deleting the lecturer with id: {}";
    public static final String SQL_FIND_LECTURER_ERROR = " :: Error while searching the lecturer with id: {}";
    public static final String SQL_FIND_ALL_LECTURERS_ERROR = " :: Error while searching all lecturers.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LecturerDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Lecturer lecturer) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("staff_id", lecturer.getStaffId());
            namedParameters.put("level", lecturer.getLevel());
            return jdbcTemplate.update(CREATE_LECTURER, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_LECTURER_ERROR, lecturer.getStaffId());
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_LECTURER,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_LECTURER_ERROR, id);
        }
    }

    @Override
    public Lecturer findById(long id) {
        try {
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

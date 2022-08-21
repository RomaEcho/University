package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.LecturerDao;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class LecturerDaoImpl implements LecturerDao<Lecturer> {
    public static final String CREATE_LECTURER = "INSERT INTO lecturers(staff_id, level) VALUES(:staff_id, :level)";
    public static final String DELETE_LECTURER = "DELETE FROM lecturers WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM lecturers WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM lecturers";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LecturerDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Lecturer lecturer) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("staff_id", lecturer.getStaffId());
        namedParameters.put("level", lecturer.getLevel());
        return jdbcTemplate.update(CREATE_LECTURER, namedParameters);
    }

    @Override
    public int delete(long id) {
        return jdbcTemplate.update(DELETE_LECTURER,
                Utils.getMapSinglePair("id", id));
    }

    @Override
    public Lecturer findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                Utils.getMapSinglePair("id", id), new LecturerMapper());
    }

    @Override
    public List<Lecturer> findAll() {
        return jdbcTemplate.query(FIND_ALL, new LecturerMapper());
    }

    class LecturerMapper implements RowMapper<Lecturer> {
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

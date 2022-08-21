package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.UniversityDao;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class UniversityDaoImpl implements UniversityDao {
    public static final String CREATE_UNIVERSITY = "INSERT INTO universities VALUES(:name, :hq_location)";
    public static final String DELETE_UNIVERSITY = "DELETE FROM universities WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM universities WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM universities";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UniversityDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(University university) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name", university.getName());
        namedParameters.put("hq_location", university.getHqLocation());
        return jdbcTemplate.update(CREATE_UNIVERSITY, namedParameters);
    }

    public int delete(long id) {
        return jdbcTemplate.update(DELETE_UNIVERSITY,
                Utils.getMapSinglePair("id", id));
    }

    public University findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                Utils.getMapSinglePair("id", id), new UniversityMapper());
    }

    public List<University> findAll() {
        return jdbcTemplate.query(FIND_ALL, new UniversityMapper());
    }

    class UniversityMapper implements RowMapper<University> {
        @Override
        public University mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            return new University(rs.getString("name"),
                    rs.getString("hq_location"));
        }
    }
}

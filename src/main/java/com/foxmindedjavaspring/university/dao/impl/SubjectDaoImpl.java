package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.SubjectDao;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class SubjectDaoImpl implements SubjectDao {
    public static final String CREATE_SUBJECT = "INSERT INTO subjects VALUES(:number, :name, :description)";
    public static final String DELETE_SUBJECT = "DELETE FROM subjects WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM subjects WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM subjects";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SubjectDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(Subject subject) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("number", subject.getNumber());
        namedParameters.put("name", subject.getName());
        namedParameters.put("description", subject.getDescription());
        return jdbcTemplate.update(CREATE_SUBJECT, namedParameters);
    }

    public int delete(long id) {
        return jdbcTemplate.update(DELETE_SUBJECT,
                Utils.getMapSinglePair("id", id));
    }

    public Subject findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                Utils.getMapSinglePair("id", id), new SubjectMapper());
    }

    public List<Subject> findAll() {
        return jdbcTemplate.query(FIND_ALL, new SubjectMapper());
    }

    class SubjectMapper implements RowMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Subject(rs.getInt("number"), rs.getString("name"));
        }
    }
}

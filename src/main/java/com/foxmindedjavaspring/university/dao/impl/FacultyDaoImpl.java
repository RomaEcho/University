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
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.model.University;

@Repository
public class FacultyDaoImpl implements GenericDao<Faculty> {
    static final String CREATE_FACULTY = 
          "INSERT INTO faculties(university_id, department, address) "
        + "VALUES((SELECT id FROM universities WHERE name = :name), :department, :address)";
    static final String DELETE_FACULTY_BY_ID = "DELETE FROM faculties WHERE id = :id";
    static final String DELETE_FACULTY = 
        "DELETE FROM "
            + "faculties "
        + "WHERE "
            + "faculties.department = :department AND "
            + "faculties.address = :address AND "
            + "faculties.university_id IN ( "
                + "SELECT "
                    + "id "
                + "FROM universities "
                + "WHERE universities.name = :name)";
    static final String FIND_BY_ID = 
          "SELECT "
            + "faculties.department AS department, "
            + "faculties.adress AS adress, "
            + "universities.name AS name, "
            + "universities.hq_location AS hq_location "
        + "FROM "
            + "faculties "
        + "JOIN universities "
            + "ON faculties.university_id = universities.id "
        + "WHERE faculties.id = :id";
    static final String FIND_ALL = 
          "SELECT "
            + "faculties.department AS department, "
            + "faculties.adress AS adress, "
            + "universities.name AS name, "
            + "universities.hq_location AS hq_location "
        + "FROM "
            + "faculties "
        + "JOIN universities "
            + "ON faculties.university_id = universities.id";
    static final String SQL_CREATE_FACULTY_ERROR = " :: Error while creating the faculty with department: {}";
    static final String SQL_DELETE_FACULTY_BY_ID_ERROR = " :: Error while deleting the faculty with id: {}";
    static final String SQL_DELETE_FACULTY_ERROR = " :: Error while deleting the faculty with university: {}, department: {}";
    static final String SQL_FIND_FACULTY_ERROR = " :: Error while searching the faculty with id: {}";
    static final String SQL_FIND_ALL_FACULTIES_ERROR = " :: Error while searching all faculties.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FacultyDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Faculty faculty) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("name", faculty.getUniversity().getName());
            namedParameters.put("department", faculty.getDepartment());
            namedParameters.put("address", faculty.getAddress());
            return jdbcTemplate.update(CREATE_FACULTY, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_FACULTY_ERROR, faculty.getDepartment());
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_FACULTY_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_FACULTY_BY_ID_ERROR, id);
        }
    }

    @Override
    public int delete(Faculty faculty) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("name", faculty.getUniversity().getName());
            namedParameters.put("department", faculty.getDepartment());
            namedParameters.put("address", faculty.getAddress());
            return jdbcTemplate.update(DELETE_FACULTY,
                    namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_FACULTY_ERROR,
                    faculty.getUniversity().getName(),
                    faculty.getDepartment());
        }
    }

    @Override
    public Faculty findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), new FacultyMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_FACULTY_ERROR, id);
        }
    }

    @Override
    public List<Faculty> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new FacultyMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_ALL_FACULTIES_ERROR);
        }
    }

    static class FacultyMapper implements RowMapper<Faculty> {
        @Override
        public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Faculty.Builder()
                    .withAddress(rs.getString("address"))
                    .withDepartment(rs.getString("department"))
                    .withUniversity(new University(rs.getString("name"),
                            rs.getString("hq_location")))
                    .build();
        }
    }
}

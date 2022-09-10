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
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.model.University;

@Repository
public class FacultyDaoImpl implements GenericDao<Faculty> {
    static final String CREATE_FACULTY = 
          "INSERT INTO faculties(university_id, department, address) "
        + "VALUES((SELECT id FROM universities WHERE name = :name), :department, :address)";
    static final String DELETE_FACULTY_BY_ID = "DELETE FROM faculties WHERE id = :id";
    static final String FIND_BY_ID = 
          "SELECT "
            + "fa.department AS department, "
            + "fa.adress AS adress, "
            + "un.name AS name, "
            + "un.hq_location AS hq_location "
        + "FROM "
            + "faculties fa"
        + "JOIN universities un"
            + "ON fa.university_id = un.id "
        + "WHERE fa.id = :id";
    static final String FIND_ALL = 
          "SELECT "
            + "fa.department AS department, "
            + "fa.adress AS adress, "
            + "un.name AS name, "
            + "un.hq_location AS hq_location "
        + "FROM "
            + "faculties fa"
        + "JOIN universities un"
            + "ON fa.university_id = un.id";
    static final String SQL_CREATE_FACULTY_ERROR = " :: Error while creating the faculty with department: {}";
    static final String SQL_DELETE_FACULTY_BY_ID_ERROR = " :: Error while deleting the faculty with id: {}";
    static final String SQL_FIND_FACULTY_ERROR = " :: Error while searching the faculty with id: {}";
    static final String SQL_FIND_ALL_FACULTIES_ERROR = " :: Error while searching all faculties.";
    private static final String DEBUG_CREATE_FACULTY = "Trying to create the faculty with department: {} using the following SQL: {}";
    private static final String DEBUG_DELETE_FACULTY = "Trying to delete the faculty with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_FACULTY = "Trying to find the faculty with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_ALL_FACULTIES = "Trying to find all the faculties using the following SQL: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(
                FacultyDaoImpl.class);

    public FacultyDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Faculty faculty) {
        try {
            LOG.debug(DEBUG_CREATE_FACULTY, faculty.getDepartment(), 
                    CREATE_FACULTY);
            Map<String, Object> namedParameters = Map.of(
                    "name", faculty.getUniversity().getName(),
                    "department", faculty.getDepartment(),
                    "address", faculty.getAddress());
            return jdbcTemplate.update(CREATE_FACULTY, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_FACULTY_ERROR, faculty.getDepartment());
        }
    }

    @Override
    public int delete(Long id) {
        try {
            LOG.debug(DEBUG_DELETE_FACULTY, id, DELETE_FACULTY_BY_ID);
            return jdbcTemplate.update(DELETE_FACULTY_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_FACULTY_BY_ID_ERROR, id);
        }
    }

    @Override
    public Faculty findById(Long id) {
        try {
            LOG.debug(DEBUG_FIND_FACULTY, id, FIND_BY_ID);
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
            LOG.debug(DEBUG_FIND_ALL_FACULTIES, FIND_ALL);
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

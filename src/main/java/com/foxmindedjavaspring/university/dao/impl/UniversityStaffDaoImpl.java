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
import com.foxmindedjavaspring.university.model.UniversityStaff;

@Repository
public class UniversityStaffDaoImpl implements GenericDao<UniversityStaff> {
    static final String CREATE_UNIVERSITY_STAFF = "INSERT INTO university_staff VALUES(:staff_id, (SELECT id FROM persons WHERE first_name = :first_name AND last_name = : last_name AND address = :address), :title)";
    static final String DELETE_UNIVERSITY_STAFF_BY_ID = "DELETE FROM university_staff WHERE id = :id";
    static final String FIND_BY_ID = "SELECT * FROM university_staff WHERE id = :id";
    static final String FIND_ALL = "SELECT * FROM university_staff";
    static final String SQL_CREATE_UNIVERSITY_STAFF_ERROR = " :: Error while creating the university_staff with staff_id: {}";
    static final String SQL_DELETE_UNIVERSITY_STAFF_ERROR_BY_ID = " :: Error while deleting the university_staff with id: {}";
    static final String SQL_FIND_UNIVERSITY_STAFF_ERROR = " :: Error while searching the university_staff with id: {}";
    static final String SQL_FIND_ALL_UNIVERSITY_STAFF_ERROR = " :: Error while searching all university_staff.";
    private static final String DEBUG_CREATE_UNIVERSITY_STAFF = "Trying to create the university_staff with staff_id: {} using the following SQL: {}";
    private static final String DEBUG_DELETE_UNIVERSITY_STAFF = "Trying to delete the university_staff with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_UNIVERSITY_STAFF = "Trying to find the university_staff with id: {} using the following SQL: {}";
    private static final String DEBUG_FIND_ALL_UNIVERSITY_STAFF = "Trying to find all the university_staff using the following SQL: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(
                UniversityStaffDaoImpl.class);

    public UniversityStaffDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(UniversityStaff universityStaff) {
        try {
            LOG.debug(DEBUG_CREATE_UNIVERSITY_STAFF, 
                    universityStaff.getStaffId(), CREATE_UNIVERSITY_STAFF);
            Map<String, Object> namedParameters = Map.of(
                    "staff_id", universityStaff.getStaffId(),
                    "first_name", universityStaff.getFirstName(),
                    "last_name", universityStaff.getLastName(),
                    "address", universityStaff.getAddress(),
                    "title", universityStaff.getTitle());
            return jdbcTemplate.update(
                    CREATE_UNIVERSITY_STAFF, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_UNIVERSITY_STAFF_ERROR,
                    universityStaff.getStaffId());
        }
    }

    @Override
    public int delete(Long id) {
        try {
            LOG.debug(DEBUG_DELETE_UNIVERSITY_STAFF, id, 
                    DELETE_UNIVERSITY_STAFF_BY_ID);
            return jdbcTemplate.update(DELETE_UNIVERSITY_STAFF_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_UNIVERSITY_STAFF_ERROR_BY_ID, id);
        }
    }

    @Override
    public UniversityStaff findById(Long id) {
        try {
            LOG.debug(DEBUG_FIND_UNIVERSITY_STAFF, id, FIND_BY_ID);
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), 
                            new UniversityStaffMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_UNIVERSITY_STAFF_ERROR, id);
        }
    }

    @Override
    public List<UniversityStaff> findAll() {
        try {
            LOG.debug(DEBUG_FIND_ALL_UNIVERSITY_STAFF, FIND_ALL);
            return jdbcTemplate.query(FIND_ALL, new UniversityStaffMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_ALL_UNIVERSITY_STAFF_ERROR);
        }
    }

    static class UniversityStaffMapper implements RowMapper<UniversityStaff> {
        @Override
        public UniversityStaff mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            return new UniversityStaff.Builder<>()
                    .withStaffId(rs.getLong("staff_id"))
                    .withTitle(rs.getString("title"))
                    .build();
        }
    }
}

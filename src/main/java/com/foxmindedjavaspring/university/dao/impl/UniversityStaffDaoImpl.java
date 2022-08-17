package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.Utils;
import com.foxmindedjavaspring.university.dao.UniversityStaffDao;
import com.foxmindedjavaspring.university.model.UniversityStaff;

@Repository
public class UniversityStaffDaoImpl implements UniversityStaffDao {
    public static final String CREATE_UNIVERSITY_STAFF = "INSERT INTO university_staff VALUES(:staff_id, " +
                        "(SELECT id FROM persons WHERE first_name = :first_name AND last_name = : last_name AND address = :address), :title)";
    public static final String DELETE_UNIVERSITY_STAFF = "DELETE FROM university_staff WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM university_staff WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM university_staff";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UniversityStaffDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    public int create(UniversityStaff universityStaff) {
        Map<String, Object> namedParameters = new HashMap<>();   
        namedParameters.put("staff_id", universityStaff.getStaffId());
        namedParameters.put("first_name", universityStaff.getFirstName());
        namedParameters.put("last_name", universityStaff.getLastName());
        namedParameters.put("address", universityStaff.getAddress());
        namedParameters.put("title", universityStaff.getTitle());
        return jdbcTemplate.update(CREATE_UNIVERSITY_STAFF, namedParameters);
    }

    public int delete(long id) {
        return jdbcTemplate.update(DELETE_UNIVERSITY_STAFF, 
                Utils.getMapSinglePair("id", id));
    }

    public UniversityStaff findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, 
                Utils.getMapSinglePair("id", id), 
                new UniversityStaffMapper());
    }
    
    public List<UniversityStaff> findAll() {
        return jdbcTemplate.query(FIND_ALL, new UniversityStaffMapper());
    }

    class UniversityStaffMapper implements RowMapper<UniversityStaff> {
    
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

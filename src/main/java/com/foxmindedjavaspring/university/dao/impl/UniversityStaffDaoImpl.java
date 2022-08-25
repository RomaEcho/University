package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.UniversityStaff;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class UniversityStaffDaoImpl implements GenericDao<UniversityStaff> {
	public static final String CREATE_UNIVERSITY_STAFF = "INSERT INTO university_staff VALUES(:staff_id, "
			+ "(SELECT id FROM persons WHERE first_name = :first_name AND last_name = : last_name AND address = :address), :title)";
	public static final String DELETE_UNIVERSITY_STAFF = "DELETE FROM university_staff WHERE id = :id";
	public static final String FIND_BY_ID = "SELECT * FROM university_staff WHERE id = :id";
	public static final String FIND_ALL = "SELECT * FROM university_staff";
	public static final String SQL_CREATE_UNIVERSITY_STAFF_ERROR = " :: Error while creating the university_staff with staff_id:";
	public static final String SQL_DELETE_UNIVERSITY_STAFF_ERROR = " :: Error while deleting the university_staff with id:";
	public static final String SQL_FIND_UNIVERSITY_STAFF_ERROR = " :: Error while searching the university_staff with id:";
	public static final String SQL_FIND_ALL_UNIVERSITY_STAFF_ERROR = " :: Error while searching all university_staff.";
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final Utils utils;

	public UniversityStaffDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, Utils utils) {
		this.jdbcTemplate = jdbcTemplate;
		this.utils = utils;
	}

	@Override
	public int create(UniversityStaff universityStaff) {
		try {
			Map<String, Object> namedParameters = new HashMap<>();
			namedParameters.put("staff_id", universityStaff.getStaffId());
			namedParameters.put("first_name", universityStaff.getFirstName());
			namedParameters.put("last_name", universityStaff.getLastName());
			namedParameters.put("address", universityStaff.getAddress());
			namedParameters.put("title", universityStaff.getTitle());
			return jdbcTemplate.update(
					CREATE_UNIVERSITY_STAFF, namedParameters);
		} catch (Exception e) {
			throw new UniversityDataAcessException(
					SQL_CREATE_UNIVERSITY_STAFF_ERROR +
							universityStaff.getStaffId(),
					e);
		}
	}

	@Override
	public int delete(long id) {
		try {
			return jdbcTemplate.update(DELETE_UNIVERSITY_STAFF,
					utils.getMapSinglePair("id", id));
		} catch (Exception e) {
			throw new UniversityDataAcessException(
					SQL_DELETE_UNIVERSITY_STAFF_ERROR + id, e);
		}
	}

	@Override
	public UniversityStaff findById(long id) {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_ID,
					utils.getMapSinglePair("id", id),
					new UniversityStaffMapper());
		} catch (Exception e) {
			throw new UniversityDataAcessException(
					SQL_FIND_UNIVERSITY_STAFF_ERROR + id, e);
		}
	}

	@Override
	public List<UniversityStaff> findAll() {
		try {
			return jdbcTemplate.query(FIND_ALL, new UniversityStaffMapper());
		} catch (Exception e) {
			throw new UniversityDataAcessException(
					SQL_FIND_ALL_UNIVERSITY_STAFF_ERROR, e);
		}
	}

	static class UniversityStaffMapper implements RowMapper<UniversityStaff> {
		@Override
		public UniversityStaff mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			return new UniversityStaff.Builder<>()
					.withStaffId(rs.getLong("staff_id"))
					.withTitle(rs.getString("title")).build();
		}
	}
}

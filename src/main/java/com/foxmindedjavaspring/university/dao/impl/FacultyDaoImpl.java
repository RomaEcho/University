package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.FacultyDao;
import com.foxmindedjavaspring.university.dao.UniversityDao;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class FacultyDaoImpl implements FacultyDao<Faculty> {
	public static final String CREATE_FACULTY = "INSERT INTO faculties(university_id, department, address) VALUES((SELECT id FROM universities WHERE name = :name), :department, :address)";
	public static final String DELETE_FACULTY = "DELETE FROM faculties WHERE id = :id";
	public static final String FIND_BY_ID = "SELECT * FROM faculties WHERE id = :id";
	public static final String FIND_ALL = "SELECT * FROM faculties";
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final FacultyMapper facultyMapper;

	public FacultyDaoImpl(NamedParameterJdbcTemplate jdbcTemplate,
			FacultyMapper facultyMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.facultyMapper = facultyMapper;
	}

	@Override
	public int create(Faculty faculty) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("name", faculty.getUniversity().getName());
		namedParameters.put("department", faculty.getDepartment());
		namedParameters.put("address", faculty.getAddress());
		return jdbcTemplate.update(CREATE_FACULTY, namedParameters);
	}

	@Override
	public int delete(long id) {
		return jdbcTemplate.update(DELETE_FACULTY,
				Utils.getMapSinglePair("id", id));
	}

	@Override
	public Faculty findById(long id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID,
				Utils.getMapSinglePair("id", id), facultyMapper);
	}

	@Override
	public List<Faculty> findAll() {
		return jdbcTemplate.query(FIND_ALL, facultyMapper);
	}

	class FacultyMapper implements RowMapper<Faculty> {
		private UniversityDao universityDao;

		public FacultyMapper(UniversityDao universityDao) {
			this.universityDao = universityDao;
		}

		@Override
		public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Faculty.Builder()
							  .withAddress(rs.getString("address"))
							  .withDepartment(rs.getString("department"))
							  .withUniversity((University) universityDao.
							  		findById (rs.getLong("university_id")))
							  .build();
		}
	}
}

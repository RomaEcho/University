package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.StudentDao;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class StudentDaoImpl implements StudentDao {
	public static final String CREATE_STUDENT = "INSERT INTO students VALUES(:staff_id, :start_date, :state)";
	public static final String DELETE_STUDENT = "DELETE FROM students WHERE id = :id";
	public static final String FIND_BY_ID = "SELECT * FROM students WHERE id = :id";
	public static final String FIND_ALL = "SELECT * FROM students";
	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public StudentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int create(Student student) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("staff_id", student.getStaffId());
		namedParameters.put("start_date", student.getStartDate());
		namedParameters.put("state", student.getState());
		return jdbcTemplate.update(CREATE_STUDENT, namedParameters);
	}

	public int delete(long id) {
		return jdbcTemplate.update(DELETE_STUDENT,
				Utils.getMapSinglePair("id", id));
	}

	public Student findById(long id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID,
				Utils.getMapSinglePair("id", id), new StudentMapper());
	}

	public List<Student> findAll() {
		return jdbcTemplate.query(FIND_ALL, new StudentMapper());
	}

	class StudentMapper implements RowMapper<Student> {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Student.Builder<>()
					.withStaffId(rs.getLong("staff_id"))
					.withStartDate(rs.getDate("start_date").toLocalDate())
					.withState(StudentState.valueOf(rs.getString("state")))
					.build();
		}
	}
}

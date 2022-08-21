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
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class StudentDaoImpl implements StudentDao<Student> {
	public static final String CREATE_STUDENT = "INSERT INTO students VALUES(:staff_id, :start_date, :state)";
	public static final String DELETE_STUDENT = "DELETE FROM students WHERE id = :id";
	public static final String FIND_BY_ID = "SELECT * FROM students WHERE id = :id";
	public static final String FIND_ALL = "SELECT * FROM students";
	private static final String SQL_CREATE_STUDENT_ERROR = " :: Error while creating the student with staff_id:";
    private static final String SQL_DELETE_STUDENT_ERROR = " :: Error while deleting the student with id:";
    private static final String SQL_FIND_STUDENT_ERROR = " :: Error while searching the student with id:";
    private static final String SQL_FIND_ALL_STUDENTS_ERROR = " :: Error while searching all students.";
	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public StudentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int create(Student student) {
		try {
			Map<String, Object> namedParameters = new HashMap<>();
			namedParameters.put("staff_id", student.getStaffId());
			namedParameters.put("start_date", student.getStartDate());
			namedParameters.put("state", student.getState());
			return jdbcTemplate.update(CREATE_STUDENT, namedParameters);
		} catch (Exception e) {
			throw new UniversityDataAcessException(
                    SQL_CREATE_STUDENT_ERROR + student.getStaffId(), e);
		}
	}

	@Override
	public int delete(long id) {
		try {
			return jdbcTemplate.update(DELETE_STUDENT,
					Utils.getMapSinglePair("id", id));
		} catch (Exception e) {
			throw new UniversityDataAcessException(
                    SQL_DELETE_STUDENT_ERROR + id, e);
		}
	}

	@Override
	public Student findById(long id) {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_ID,
					Utils.getMapSinglePair("id", id), new StudentMapper());
		} catch (Exception e) {
			throw new UniversityDataAcessException(
                    SQL_FIND_STUDENT_ERROR + id, e);
		}
	}

	@Override
	public List<Student> findAll() {
		try {
			return jdbcTemplate.query(FIND_ALL, new StudentMapper());
		} catch (Exception e) {
			throw new UniversityDataAcessException(
                    SQL_FIND_ALL_STUDENTS_ERROR, e);
		}
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

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
import com.foxmindedjavaspring.university.model.Person;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class PersonDaoImpl implements GenericDao<Person> {
	public static final String CREATE_PERSON = "INSERT INTO persons VALUES(:first_name, :last_name, :birth_day, :gender, :phone, :email, :address)";
	public static final String DELETE_PERSON = "DELETE FROM persons WHERE id = :id";
	public static final String FIND_BY_ID = "SELECT * FROM persons WHERE id = :id";
	public static final String FIND_ALL = "SELECT * FROM persons";
	public static final String SQL_CREATE_PERSON_ERROR = " :: Error while creating the person with:";
	public static final String SQL_DELETE_PERSON_ERROR = " :: Error while deleting the person with id:";
	public static final String SQL_FIND_PERSON_ERROR = " :: Error while searching the person with id:";
	public static final String SQL_FIND_ALL_PERSONS_ERROR = " :: Error while searching all persons.";
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final Utils utils;

	public PersonDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, Utils utils) {
		this.jdbcTemplate = jdbcTemplate;
		this.utils = utils;
	}

	@Override
	public int create(Person person) {
		try {
			Map<String, Object> namedParameters = new HashMap<>();
			namedParameters.put("first_name", person.getFirstName());
			namedParameters.put("last_name", person.getLastName());
			namedParameters.put("birth_day", person.getBirthday());
			namedParameters.put("gender", person.getGender());
			namedParameters.put("phon", person.getPhone());
			namedParameters.put("email", person.getEmail());
			namedParameters.put("address", person.getAddress());
			return jdbcTemplate.update(CREATE_PERSON, namedParameters);
		} catch (Exception e) {
			throw new UniversityDataAcessException(String.format(
					"%s first_name: %s, last_name: %s, address: %s.",
					SQL_CREATE_PERSON_ERROR,
					person.getFirstName(),
					person.getLastName(),
					person.getAddress()),
					e);
		}
	}

	@Override
	public int delete(long id) {
		try {
			return jdbcTemplate.update(DELETE_PERSON,
					utils.getMapSinglePair("id", id));
		} catch (Exception e) {
			throw new UniversityDataAcessException(
					SQL_DELETE_PERSON_ERROR + id, e);
		}
	}

	@Override
	public Person findById(long id) {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_ID,
					utils.getMapSinglePair("id", id), new PersonMapper());
		} catch (Exception e) {
			throw new UniversityDataAcessException(
					SQL_FIND_PERSON_ERROR + id, e);
		}
	}

	@Override
	public List<Person> findAll() {
		try {
			return jdbcTemplate.query(FIND_ALL, new PersonMapper());
		} catch (Exception e) {
			throw new UniversityDataAcessException(
					SQL_FIND_ALL_PERSONS_ERROR, e);
		}
	}

	static class PersonMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Person.Builder()
					.withAddress(rs.getString("address"))
					.withBirthday(rs.getDate("birth_day").toLocalDate())
					.withEmail(rs.getString("email"))
					.withFirstName(rs.getString("first_name"))
					.withGender(rs.getString("gender"))
					.withLastName(rs.getString("last_name"))
					.withPhone(rs.getString("phone"))
					.build();
		}
	}
}

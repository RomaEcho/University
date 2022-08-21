package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.PersonDao;
import com.foxmindedjavaspring.university.model.Person;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class PersonDaoImpl implements PersonDao<Person> {
	public static final String CREATE_PERSON = "INSERT INTO persons VALUES(:first_name, :last_name, :birth_day, :gender, :phone, :email, :address)";
	public static final String DELETE_PERSON = "DELETE FROM persons WHERE id = :id";
	public static final String FIND_BY_ID = "SELECT * FROM persons WHERE id = :id";
	public static final String FIND_ALL = "SELECT * FROM persons";
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public PersonDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int create(Person person) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("first_name", person.getFirstName());
		namedParameters.put("last_name", person.getLastName());
		namedParameters.put("birth_day", person.getBirthday());
		namedParameters.put("gender", person.getGender());
		namedParameters.put("phon", person.getPhone());
		namedParameters.put("email", person.getEmail());
		namedParameters.put("address", person.getAddress());
		return jdbcTemplate.update(CREATE_PERSON, namedParameters);
	}

	@Override
	public int delete(long id) {
		return jdbcTemplate.update(DELETE_PERSON,
				Utils.getMapSinglePair("id", id));
	}

	@Override
	public Person findById(long id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID,
				Utils.getMapSinglePair("id", id), new PersonMapper());
	}

	@Override
	public List<Person> findAll() {
		return jdbcTemplate.query(FIND_ALL, new PersonMapper());
	}

	class PersonMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Person.Builder().withAddress(rs.getString("address"))
					.withBirthday(rs.getDate("birth_day").toLocalDate())
					.withEmail(rs.getString("email"))
					.withFirstName(rs.getString("first_name"))
					.withGender(rs.getString("gender"))
					.withLastName(rs.getString("last_name"))
					.withPhone(rs.getString("phone")).build();
		}
	}
}

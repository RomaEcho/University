package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.PersonDao;
import com.foxmindedjavaspring.university.model.Person;

@Component
public class PersonDaoImpl implements PersonDao {
    private static final String ADD_PERSON = "INSERT INTO persons VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_PERSON = "DELETE FROM persons WHERE first_name = ? AND last_name = ? AND address = ?";
    private static final String UPDATE_PHONE = "UPDATE persons SET phone = ? WHERE first_name = ? AND last_name = ? AND address = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Person person) {
        jdbcTemplate.update(ADD_PERSON, person.getFirstName(),
                person.getLastName(), person.getBirthday(),
                person.getGender(), person.getPhone(), person.getEmail(),
                person.getAddress());
    }

    @Override
    public void delete(Person person) {
        jdbcTemplate.update(REMOVE_PERSON, person.getFirstName(),
                person.getLastName(), person.getAddress());
    }

    @Override
    public void updatePhoneNumber(Person person, String phone) {
        jdbcTemplate.update(UPDATE_PHONE, phone, person.getFirstName(),
                person.getLastName(), person.getAddress());
    }
}

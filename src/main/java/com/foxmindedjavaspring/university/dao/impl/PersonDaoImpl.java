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
    public boolean create(Person person) {
        return jdbcTemplate.update(ADD_PERSON, person.getFirstName(),
                person.getLastName(), person.getBirthday(),
                person.getGender(), person.getPhone(), person.getEmail(),
                person.getAddress()) == 1;
    }

    @Override
    public boolean delete(Person person) {
        return jdbcTemplate.update(REMOVE_PERSON, person.getFirstName(),
                person.getLastName(), person.getAddress()) == 1;
    }

    @Override
    public boolean updatePhoneNumber(Person person, String phone) {
        return jdbcTemplate.update(UPDATE_PHONE, phone, person.getFirstName(),
                person.getLastName(), person.getAddress()) == 1;
    }
}

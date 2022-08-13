package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.model.Person;

class PersonDaoImplTest {
    private Person person;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private PersonDaoImpl personDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(personDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        person = new Person.Builder<>()
                           .withFirstName("firstName")
                           .withLastName("lastName")
                           .withBirthday(LocalDate.of(2017, 1, 13))
                           .withGender("male")
                           .withPhone("222")
                           .withEmail("email")
                           .withAddress("address")
                           .build();
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingPerson() {
        when(jdbcTemplate.update(PersonDaoImpl.REMOVE_PERSON,
                person.getFirstName(), person.getLastName(),
                person.getAddress())).thenReturn(1);
        assertTrue(personDaoImpl.delete(person));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingPerson() {
        when(jdbcTemplate.update(PersonDaoImpl.ADD_PERSON,
                person.getFirstName(), person.getLastName(),
                person.getBirthday(), person.getGender(), person.getPhone(),
                person.getEmail(), person.getAddress())).thenReturn(1);
        assertTrue(personDaoImpl.create(person));
    }

    @Test
    void shouldVerifyReturnValue_whileUpdatingPhoneNumber() {
        String phone = "111";
        when(jdbcTemplate.update(PersonDaoImpl.UPDATE_PHONE, phone,
                person.getFirstName(), person.getLastName(),
                person.getAddress())).thenReturn(1);
        assertTrue(personDaoImpl.updatePhoneNumber(person, phone));
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.dao.impl.PersonDaoImpl.PersonMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Person;

class PersonDaoImplTest {
    private static final int expected = 1;
    private static final int id = 111;
    private List<Person> persons;
    private Person person;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private PersonDaoImpl personDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(personDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        person = new Person.Builder<>().withFirstName("firstName")
                .withLastName("lastName")
                .withBirthday(LocalDate.of(2017, 1, 13)).withGender("male")
                .withPhone("222").withEmail("email").withAddress("address")
                .build();
        persons = List.of(person);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingPerson() {
        when(jdbcTemplate.update(eq(PersonDaoImpl.CREATE_PERSON), anyMap())).
                thenReturn(1);

        int actual = personDaoImpl.create(person);

        verify(jdbcTemplate).update(eq(PersonDaoImpl.CREATE_PERSON), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingPerson() {
        when(jdbcTemplate.update(eq(PersonDaoImpl.CREATE_PERSON), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                PersonDaoImpl.SQL_CREATE_PERSON_ERROR.replace("{}", "%s"), 
                person.getFirstName(),
                person.getLastName(),
                person.getAddress());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> personDaoImpl.create(person));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(PersonDaoImpl.CREATE_PERSON), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingPersonById() {
        when(jdbcTemplate.update(eq(PersonDaoImpl.DELETE_PERSON_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = personDaoImpl.delete(id);

        verify(jdbcTemplate).update(eq(PersonDaoImpl.DELETE_PERSON_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingPersonById() {
        when(jdbcTemplate.update(eq(PersonDaoImpl.DELETE_PERSON_BY_ID), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                PersonDaoImpl.SQL_DELETE_PERSON_BY_ID_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> personDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(PersonDaoImpl.DELETE_PERSON_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingPerson() {
        when(jdbcTemplate.queryForObject(eq(PersonDaoImpl.FIND_BY_ID), anyMap(),
                any(PersonMapper.class))).thenReturn(person);

        Person returnPerson = personDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(eq(PersonDaoImpl.FIND_BY_ID), 
                anyMap(), any(PersonMapper.class));
        assertNotNull(returnPerson);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingPerson() {
        when(jdbcTemplate.queryForObject(eq(PersonDaoImpl.FIND_BY_ID), anyMap(),
                any(PersonMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                PersonDaoImpl.SQL_FIND_PERSON_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> personDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(PersonDaoImpl.FIND_BY_ID), 
                anyMap(), any(PersonMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllPersons() {
        when(jdbcTemplate.query(eq(PersonDaoImpl.FIND_ALL), 
                any(PersonMapper.class))).thenReturn(persons);

        int actual = personDaoImpl.findAll().size();

        verify(jdbcTemplate).query(eq(PersonDaoImpl.FIND_ALL),
                any(PersonMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllPersons() {
        when(jdbcTemplate.query(eq(PersonDaoImpl.FIND_ALL), 
                any(PersonMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = PersonDaoImpl.SQL_FIND_ALL_PERSONS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> personDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(PersonDaoImpl.FIND_ALL), 
                any(PersonMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
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
    private static final String SPLITTER = ":";
    private static final int COMPARED_PART = 2;
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
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = personDaoImpl.create(person);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingPerson() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> personDaoImpl.create(person));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(PersonDaoImpl.SQL_CREATE_PERSON_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(person.getFirstName()));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingPersonById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = personDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingPersonById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> personDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(PersonDaoImpl.
                        SQL_DELETE_PERSON_BY_ID_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingPerson() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = personDaoImpl.delete(person);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingPerson() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> personDaoImpl.delete(person));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(PersonDaoImpl.SQL_DELETE_PERSON_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(person.getFirstName()));
        assertTrue(actualMessage.contains(person.getLastName()));
        assertTrue(actualMessage.contains(person.getAddress()));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingPerson() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(PersonMapper.class))).thenReturn(person);

        Person returnPerson = personDaoImpl.findById(id);

        assertNotNull(returnPerson);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingPerson() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(PersonMapper.class))).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> personDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(PersonDaoImpl.SQL_FIND_PERSON_ERROR
                .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllPersons() {
        when(jdbcTemplate.query(anyString(), any(PersonMapper.class)))
                .thenReturn(persons);

        int actual = personDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllPersons() {
        when(jdbcTemplate.query(anyString(), any(PersonMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> personDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(PersonDaoImpl.SQL_FIND_ALL_PERSONS_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }
}

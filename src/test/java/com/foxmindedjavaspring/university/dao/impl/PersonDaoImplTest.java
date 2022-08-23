package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.foxmindedjavaspring.university.utils.Utils;

class PersonDaoImplTest {
    private Person person;
	private List<Person> persons;
	private int expected;
	private int actual;
	private int id;
	@Mock
	private Utils utils;
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
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
        id = 111;
		expected = 1;
		persons = new ArrayList<>();
		persons.add(person);
    }

    @Test
	void shouldVerifyReturnValue_whileCreatingPerson() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		actual = personDaoImpl.create(person);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileCreatingPerson() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.update(anyString(), anyMap());

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> personDaoImpl.create(person));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(PersonDaoImpl.SQL_CREATE_PERSON_ERROR));
		assertTrue(actualMessage.contains(person.getFirstName()));
	}

	@Test
	void shouldVerifyReturnValue_whileDeletingPerson() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		actual = personDaoImpl.delete(id);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileDeletingPerson() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.update(anyString(), anyMap());

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> personDaoImpl.delete(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(PersonDaoImpl.SQL_DELETE_PERSON_ERROR));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingPerson() {
		when(jdbcTemplate.queryForObject(anyString(), anyMap(),
				any(PersonMapper.class)))
				.thenReturn(person);

		Person returnPerson = personDaoImpl.findById(id);

		assertNotNull(returnPerson);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingPerson() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.queryForObject(anyString(), anyMap(),
						any(PersonMapper.class));

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> personDaoImpl.findById(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(PersonDaoImpl.SQL_FIND_PERSON_ERROR));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingAllPersons() {
		when(jdbcTemplate.query(anyString(), any(PersonMapper.class)))
				.thenReturn(persons);

		int actual = personDaoImpl.findAll().size();

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingAllPersons() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.query(anyString(), any(PersonMapper.class));

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> personDaoImpl.findAll());
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(PersonDaoImpl.SQL_FIND_ALL_PERSONS_ERROR));
	}
}

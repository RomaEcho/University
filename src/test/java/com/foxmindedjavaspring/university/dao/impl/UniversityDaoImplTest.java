package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.dao.impl.UniversityDaoImpl.UniversityMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.University;

class UniversityDaoImplTest {
	private static final String SPLITTER = ":";
	private static final int COMPARED_PART = 2;
	private static final int id = 111;
	private static final int expected = 1;
	private List<University> universities;
	private University university;
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	@InjectMocks
	private UniversityDaoImpl universityDaoImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(universityDaoImpl, "jdbcTemplate",
				jdbcTemplate);
		university = new University("name", "hqLocation");
		universities = List.of(university);
	}

	@Test
	void shouldVerifyReturnValue_whileCreatingUniversity() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		int actual = universityDaoImpl.create(university);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileCreatingUniversity() {
		when(jdbcTemplate.update(anyString(), anyMap()))
				.thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> universityDaoImpl.create(university));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(UniversityDaoImpl.SQL_CREATE_UNIVERSITY_ERROR
						.split(SPLITTER)[COMPARED_PART]));
		assertTrue(actualMessage.contains(university.getName()));
	}

	@Test
	void shouldVerifyReturnValue_whileDeletingUniversity() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		int actual = universityDaoImpl.delete(id);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileDeletingUniversity() {
		when(jdbcTemplate.update(anyString(), anyMap()))
				.thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> universityDaoImpl.delete(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(UniversityDaoImpl.SQL_DELETE_UNIVERSITY_ERROR
						.split(SPLITTER)[COMPARED_PART]));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingUniversity() {
		when(jdbcTemplate.queryForObject(anyString(), anyMap(),
				any(UniversityMapper.class))).thenReturn(university);

		University returnUniversity = universityDaoImpl.findById(id);

		assertNotNull(returnUniversity);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingUniversity() {
		when(jdbcTemplate.queryForObject(anyString(), anyMap(),
				any(UniversityMapper.class)))
				.thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> universityDaoImpl.findById(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(UniversityDaoImpl.SQL_FIND_UNIVERSITY_ERROR
						.split(SPLITTER)[COMPARED_PART]));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingAllUniversities() {
		when(jdbcTemplate.query(anyString(), any(UniversityMapper.class)))
				.thenReturn(universities);

		int actual = universityDaoImpl.findAll().size();

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingAllUniversities() {
		when(jdbcTemplate.query(anyString(), any(UniversityMapper.class)))
				.thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> universityDaoImpl.findAll());
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(UniversityDaoImpl.SQL_FIND_ALL_UNIVERSITIES_ERROR
						.split(SPLITTER)[COMPARED_PART]));
	}
}

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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.dao.impl.CourseDaoImpl.CourseMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.utils.Utils;

class CourseDaoImplTest {
	private List<Course> courses;
	private Course course;
	private int expected;
	private int actual;
	private int id;
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Mock
	private Utils utils;
	@Mock
	private CourseMapper courseMapper;
	@InjectMocks
	private CourseDaoImpl courseDaoImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(courseDaoImpl, "jdbcTemplate",
				jdbcTemplate);
		course = new Course.Builder()
				.withTopic("topic")
				.withNumberOfHours(22)
				.build();
		id = 111;
		expected = 1;
		courses = new ArrayList<>();
		courses.add(course);
	}

	@Test
	void shouldVerifyReturnValue_whileCreatingCourse() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		actual = courseDaoImpl.create(course);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileCreatingCourse() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.update(anyString(), anyMap());

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> courseDaoImpl.create(course));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(CourseDaoImpl.SQL_CREATE_COURSE_ERROR));
		assertTrue(actualMessage.contains(course.getTopic()));
	}

	@Test
	void shouldVerifyReturnValue_whileDeletingCourse() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		actual = courseDaoImpl.delete(id);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileDeletingCourse() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.update(anyString(), anyMap());

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> courseDaoImpl.delete(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(CourseDaoImpl.SQL_DELETE_COURSE_ERROR));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingCourse() {
		when(jdbcTemplate.queryForObject(anyString(), anyMap(),
				any(CourseMapper.class)))
				.thenReturn(course);

		Course returnCourse = courseDaoImpl.findById(id);

		assertNotNull(returnCourse);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingCourse() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.queryForObject(anyString(), anyMap(),
						any(CourseMapper.class));

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> courseDaoImpl.findById(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(CourseDaoImpl.SQL_FIND_COURSE_ERROR));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingAllCourses() {
		when(jdbcTemplate.query(anyString(), any(CourseMapper.class)))
				.thenReturn(courses);

		int actual = courseDaoImpl.findAll().size();

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingAllCourses() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.query(anyString(), any(CourseMapper.class));

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> courseDaoImpl.findAll());
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(CourseDaoImpl.SQL_FIND_ALL_COURSES_ERROR));
	}
}

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

import com.foxmindedjavaspring.university.dao.impl.CourseDaoImpl.CourseMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Course;

class CourseDaoImplTest {
	private static final int COMPARED_PART = 2;
	private static final String SPLITTER = ":";
	private static final int expected = 1;
	private static final int id = 111;
	private List<Course> courses;
	private Course course;
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Mock
	private CourseMapper courseMapper;
	@InjectMocks
	private CourseDaoImpl courseDaoImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(courseDaoImpl, "jdbcTemplate",
				jdbcTemplate);
		course = new Course.Builder().withTopic("topic")
				.withNumberOfHours(22).build();
		courses = List.of(course);
	}

	@Test
	void shouldVerifyReturnValue_whileCreatingCourse() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		int actual = courseDaoImpl.create(course);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileCreatingCourse() {
		when(jdbcTemplate.update(anyString(), anyMap()))
				.thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> courseDaoImpl.create(course));
		String actualMessage = exception.getMessage();

		assertTrue(
				actualMessage.contains(CourseDaoImpl.SQL_CREATE_COURSE_ERROR
						.split(SPLITTER)[COMPARED_PART]));
		assertTrue(actualMessage.contains(course.getTopic()));
	}

	@Test
	void shouldVerifyReturnValue_whileDeletingCourse() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		int actual = courseDaoImpl.delete(id);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileDeletingCourse() {
		when(jdbcTemplate.update(anyString(), anyMap()))
				.thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> courseDaoImpl.delete(id));
		String actualMessage = exception.getMessage();

		assertTrue(
				actualMessage.contains(CourseDaoImpl.SQL_DELETE_COURSE_ERROR
						.split(SPLITTER)[COMPARED_PART]));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingCourse() {
		when(jdbcTemplate.queryForObject(anyString(), anyMap(),
				any(CourseMapper.class))).thenReturn(course);

		Course returnCourse = courseDaoImpl.findById(id);

		assertNotNull(returnCourse);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingCourse() {
		when(jdbcTemplate.queryForObject(anyString(), anyMap(),
				any(CourseMapper.class))).thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> courseDaoImpl.findById(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(CourseDaoImpl.SQL_FIND_COURSE_ERROR
				.split(SPLITTER)[COMPARED_PART]));
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
	when(jdbcTemplate.query(anyString(), any(CourseMapper.class)))
		.thenThrow(RuntimeException.class);

	Exception exception = assertThrows(
		UniversityDataAcessException.class,
		() -> courseDaoImpl.findAll());
	String actualMessage = exception.getMessage();

	assertTrue(actualMessage
		.contains(CourseDaoImpl.SQL_FIND_ALL_COURSES_ERROR
			.split(SPLITTER)[COMPARED_PART]));
    }
}

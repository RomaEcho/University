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
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.model.Subject;

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
        course = new Course.Builder()
                          .withTopic("topic")
                          .withNumberOfHours(22)
                          .withLecturer(new Lecturer.Builder<>()
                                            .withStaffId((long) 111)
                                            .build())
                          .withSubject(new Subject(111, "name"))
                          .build();
        courses = List.of(course);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingCourse() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = courseDaoImpl.create(course);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingCourse() {
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
    void shouldVerifyReturnValueWhileDeletingCourseById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = courseDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCourseById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(CourseDaoImpl.
                        SQL_DELETE_COURSE_BY_ID_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingCourse() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = courseDaoImpl.delete(course);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCourse() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDaoImpl.delete(course));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(CourseDaoImpl.SQL_DELETE_COURSE_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(course.getTopic()));
        assertTrue(actualMessage.contains(course.getLecturer().getStaffId()
                .toString()));
        assertTrue(actualMessage.contains(course.getSubject().getName()));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingCourse() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(CourseMapper.class))).thenReturn(course);

        Course returnCourse = courseDaoImpl.findById(id);

        assertNotNull(returnCourse);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingCourse() {
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
    void shouldVerifyReturnValueWhileSearchingAllCourses() {
        when(jdbcTemplate.query(anyString(), any(CourseMapper.class)))
                .thenReturn(courses);

        int actual = courseDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllCourses() {
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

package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingCourse() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CourseDaoImpl.SQL_CREATE_COURSE_ERROR.replace("{}", "%s"), 
                course.getTopic());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDaoImpl.create(course));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingCourseById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = courseDaoImpl.delete(id);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCourseById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CourseDaoImpl.SQL_DELETE_COURSE_BY_ID_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingCourse() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = courseDaoImpl.delete(course);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCourse() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CourseDaoImpl.SQL_DELETE_COURSE_ERROR.replace("{}", "%s"), 
                course.getTopic(),
                course.getLecturer().getStaffId(),
                course.getSubject().getName());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDaoImpl.delete(course));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingCourse() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(CourseMapper.class))).thenReturn(course);

        Course returnCourse = courseDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(CourseMapper.class));
        assertNotNull(returnCourse);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingCourse() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(CourseMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CourseDaoImpl.SQL_FIND_COURSE_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(CourseMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllCourses() {
        when(jdbcTemplate.query(anyString(), any(CourseMapper.class)))
                .thenReturn(courses);

        int actual = courseDaoImpl.findAll().size();

        verify(jdbcTemplate).query(anyString(), any(CourseMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllCourses() {
        when(jdbcTemplate.query(anyString(), any(CourseMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = CourseDaoImpl.SQL_FIND_ALL_COURSES_ERROR;

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(anyString(), any(CourseMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

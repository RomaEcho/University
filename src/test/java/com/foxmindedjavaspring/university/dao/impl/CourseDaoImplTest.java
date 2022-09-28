package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
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
    private static final int expected = 1;
    private static final Long id = (long) 111;
    private List<Course> courses;
    private Course course;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Mock
    private CourseMapper courseMapper;
    @InjectMocks
    private CourseDaoImpl courseDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(courseDao, "jdbcTemplate",
                jdbcTemplate);
        course = new Course.Builder()
                          .withTopic("topic")
                          .withNumberOfHours(22)
                          .withLecturer(new Lecturer.Builder<>()
                                            .withStaffId((long) 111)
                                            .build())
                          .withSubject(new Subject((long) 111, 111, "name"))
                          .build();
        courses = List.of(course);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingCourse() {
        when(jdbcTemplate.update(eq(CourseDaoImpl.CREATE_COURSE), anyMap()))
                .thenReturn(1);

        int actual = courseDao.create(course);

        verify(jdbcTemplate).update(eq(CourseDaoImpl.CREATE_COURSE), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingCourse() {
        when(jdbcTemplate.update(eq(CourseDaoImpl.CREATE_COURSE), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CourseDaoImpl.SQL_CREATE_COURSE_ERROR.replace("{}", "%s"), 
                course.getTopic());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDao.create(course));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(CourseDaoImpl.CREATE_COURSE), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingCourseById() {
        when(jdbcTemplate.update(eq(CourseDaoImpl.DELETE_COURSE_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = courseDao.delete(id);

        verify(jdbcTemplate).update(eq(CourseDaoImpl.DELETE_COURSE_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCourseById() {
        when(jdbcTemplate.update(eq(CourseDaoImpl.DELETE_COURSE_BY_ID), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CourseDaoImpl.SQL_DELETE_COURSE_BY_ID_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(CourseDaoImpl.DELETE_COURSE_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingCourse() {
        when(jdbcTemplate.queryForObject(eq(CourseDaoImpl.FIND_BY_ID), anyMap(),
                any(CourseMapper.class))).thenReturn(course);

        Course returnCourse = courseDao.findById(id);

        verify(jdbcTemplate).queryForObject(eq(CourseDaoImpl.FIND_BY_ID), 
                anyMap(), any(CourseMapper.class));
        assertNotNull(returnCourse);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingCourse() {
        when(jdbcTemplate.queryForObject(eq(CourseDaoImpl.FIND_BY_ID), anyMap(),
                any(CourseMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CourseDaoImpl.SQL_FIND_COURSE_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(CourseDaoImpl.FIND_BY_ID), 
                anyMap(), any(CourseMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllCourses() {
        when(jdbcTemplate.query(eq(CourseDaoImpl.FIND_ALL), 
                any(CourseMapper.class))).thenReturn(courses);

        int actual = courseDao.findAll().size();

        verify(jdbcTemplate).query(eq(CourseDaoImpl.FIND_ALL), 
                any(CourseMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllCourses() {
        when(jdbcTemplate.query(eq(CourseDaoImpl.FIND_ALL), 
                any(CourseMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = CourseDaoImpl.SQL_FIND_ALL_COURSES_ERROR;

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> courseDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(CourseDaoImpl.FIND_ALL), 
                any(CourseMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

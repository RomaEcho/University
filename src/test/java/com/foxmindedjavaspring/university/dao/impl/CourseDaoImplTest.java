package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.model.Course;

class CourseDaoImplTest {
    private Course course;
    @Mock
    JdbcTemplate jdbcTemplate;
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
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingCourse() {
        when(jdbcTemplate.update(CourseDaoImpl.REMOVE_COURSE, course.getTopic(),
        	course.getNumberOfHours())).thenReturn(1);
        assertTrue(courseDaoImpl.delete(course));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingCourse() {
        when(jdbcTemplate.update(CourseDaoImpl.ADD_COURSE, course.getTopic(),
        	course.getNumberOfHours())).thenReturn(1);
        assertTrue(courseDaoImpl.create(course));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingRate() {
        int rate = 5;
        when(jdbcTemplate.update(CourseDaoImpl.ADD_RATE, rate, course.getTopic(),
        	course.getNumberOfHours())).thenReturn(1);
        assertTrue(courseDaoImpl.addRate(course, rate));
    }
}

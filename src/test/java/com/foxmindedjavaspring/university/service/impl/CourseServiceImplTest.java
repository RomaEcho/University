package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.CourseDao;
import com.foxmindedjavaspring.university.model.Course;

public class CourseServiceImplTest {
    private static final long id = 11;
    private Course course;
    @Mock
    private CourseDao courseDao;
    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        course = new Course();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewCourse() {
        courseService.addCourse(course);

        verify(courseDao).create(course);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingCourse() {
        courseService.removeCourse(course);

        verify(courseDao).delete(course);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingCourse() {
        courseService.getCourse(id);

        verify(courseDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllCourses() {
        courseService.getAllCourses();

        verify(courseDao).findAll();
    }
}
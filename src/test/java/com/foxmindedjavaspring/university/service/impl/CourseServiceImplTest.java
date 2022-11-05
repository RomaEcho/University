package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Course;

import java.util.Optional;

public class CourseServiceImplTest {
    private static final long id = 11;
    private Course course;
    @Mock
    private CourseRepository courseRepository;
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

        verify(courseRepository).save(course);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingCourse() {
        courseService.editCourse(course);

        verify(courseRepository).save(course);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingCourse() {
        courseService.removeCourse(course);

        verify(courseRepository).delete(course);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingCourse() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));

        courseService.getCourse(id);

        verify(courseRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllCourses() {
        courseService.getAllCourses();

        verify(courseRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingCourse() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                CourseServiceImpl.GET_COURSE_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> courseService.getCourse(id));
        String actualMessage = exception.getMessage();

        verify(courseRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}
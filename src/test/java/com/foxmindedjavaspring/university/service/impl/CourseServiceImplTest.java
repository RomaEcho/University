package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.model.Subject;

public class CourseServiceImplTest {
    private static final long id = 11;
    private Course course;
    @Mock
    private GenericDao<Course> genericDao;
    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        course = new Course.Builder()
                          .withTopic("topic")
                          .withNumberOfHours(22)
                          .withLecturer(new Lecturer.Builder<>()
                                            .withStaffId((long) 111)
                                            .build())
                          .withSubject(new Subject(111, "name"))
                          .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewCourse() {
        courseServiceImpl.addCourse(course);

        verify(genericDao).create(course);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingCourse() {
        courseServiceImpl.removeCourse(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingCourse() {
        courseServiceImpl.getCourse(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllCourses() {
        courseServiceImpl.getAllCourses();

        verify(genericDao).findAll();
    }
}
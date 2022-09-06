package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;

public class StudentServiceImplTest {
    private static final long id = 11;
    private Student student;
    @Mock
    private GenericDao<Student> genericDao;
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student.Builder<>()
                             .withStaffId((long) 11)
                             .withStartDate(LocalDate.of(2017, 1, 13))
                             .withState(StudentState.ACTIVE)
                             .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewStudent() {
        studentServiceImpl.addStudent(student);

        verify(genericDao).create(student);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingStudent() {
        studentServiceImpl.removeStudent(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingStudent() {
        studentServiceImpl.getStudent(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllStudents() {
        studentServiceImpl.getAllStudents();

        verify(genericDao).findAll();
    }
}

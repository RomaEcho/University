package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.StudentDao;
import com.foxmindedjavaspring.university.model.Student;

public class StudentServiceImplTest {
    private static final long id = 11;
    private Student student;
    @Mock
    private StudentDao studentDao;
    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewStudent() {
        studentService.addStudent(student);

        verify(studentDao).create(student);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingStudent() {
        studentService.removeStudent(student);

        verify(studentDao).delete(student);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingStudent() {
        studentService.getStudent(id);

        verify(studentDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllStudents() {
        studentService.getAllStudents();

        verify(studentDao).findAll();
    }
}

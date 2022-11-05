package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Student;

import java.util.Optional;

public class StudentServiceImplTest {
    private static final long id = 11;
    private Student student;
    @Mock
    private StudentRepository studentRepository;
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

        verify(studentRepository).save(student);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingStudent() {
        studentService.editStudent(student);

        verify(studentRepository).save(student);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingStudent() {
        studentService.removeStudent(student);

        verify(studentRepository).delete(student);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.getStudent(id);

        verify(studentRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllStudents() {
        studentService.getAllStudents();

        verify(studentRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                StudentServiceImpl.GET_STUDENT_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> studentService.getStudent(id));
        String actualMessage = exception.getMessage();

        verify(studentRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}

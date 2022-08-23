package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.dao.impl.StudentDaoImpl.StudentMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;
import com.foxmindedjavaspring.university.utils.Utils;

class StudentDaoImplTest {
    private Student student;
    private List<Student> students;
    private int expected;
    private int actual;
    private int id;
    @Mock
    private Utils utils;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private StudentDaoImpl studentDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(studentDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        student = new Student.Builder<>()
                            .withStaffId(11)
                            .withStartDate(LocalDate.of(2017, 1, 13))
                            .withState(StudentState.ACTIVE)
                            .build();
        id = 111;
        expected = 1;
        students = new ArrayList<>();
        students.add(student);
    }

    @Test
    void shouldVerifyReturnValue_whileCreatingStudent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = studentDaoImpl.create(student);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileCreatingStudent() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> studentDaoImpl.create(student));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(StudentDaoImpl.SQL_CREATE_STUDENT_ERROR));
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingStudent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = studentDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileDeletingStudent() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> studentDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(StudentDaoImpl.SQL_DELETE_STUDENT_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingStudent() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(StudentMapper.class)))
                .thenReturn(student);

        Student returnStudent = studentDaoImpl.findById(id);

        assertNotNull(returnStudent);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingStudent() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .queryForObject(anyString(), anyMap(),
                        any(StudentMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> studentDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(StudentDaoImpl.SQL_FIND_STUDENT_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingAllStudents() {
        when(jdbcTemplate.query(anyString(), any(StudentMapper.class)))
                .thenReturn(students);

        int actual = studentDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingAllStudents() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .query(anyString(), any(StudentMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> studentDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(StudentDaoImpl.SQL_FIND_ALL_STUDENTS_ERROR));
    }
}

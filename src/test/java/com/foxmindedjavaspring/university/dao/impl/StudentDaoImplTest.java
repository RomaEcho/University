package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

class StudentDaoImplTest {
    private static final int expected = 1;
    private static final int id = 111;
    private List<Student> students;
    private Student student;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private StudentDaoImpl studentDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(studentDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        student = new Student.Builder<>().withStaffId((long) 11)
                .withStartDate(LocalDate.of(2017, 1, 13))
                .withState(StudentState.ACTIVE).build();
        students = List.of(student);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingStudent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = studentDaoImpl.create(student);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingStudent() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                StudentDaoImpl.SQL_CREATE_STUDENT_ERROR.replace("{}", "%s"), 
                student.getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDaoImpl.create(student));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingStudentById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = studentDaoImpl.delete(id);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingStudentById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                StudentDaoImpl.SQL_DELETE_STUDENT_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingStudent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = studentDaoImpl.delete(student);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingStudent() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(StudentDaoImpl.
                SQL_DELETE_STUDENT_ERROR.replace("{}", "%s"), 
                student.getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDaoImpl.delete(student));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingStudent() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(StudentMapper.class))).thenReturn(student);

        Student returnStudent = studentDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(StudentMapper.class));
        assertNotNull(returnStudent);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingStudent() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(StudentMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                StudentDaoImpl.SQL_FIND_STUDENT_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(StudentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllStudents() {
        when(jdbcTemplate.query(anyString(), any(StudentMapper.class)))
                .thenReturn(students);

        int actual = studentDaoImpl.findAll().size();

        verify(jdbcTemplate).query(anyString(), any(StudentMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllStudents() {
        when(jdbcTemplate.query(anyString(), any(StudentMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = StudentDaoImpl.SQL_FIND_ALL_STUDENTS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(anyString(), any(StudentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

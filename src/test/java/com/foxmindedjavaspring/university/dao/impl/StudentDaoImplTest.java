package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
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
    private static final Long id = (long) 111;
    private List<Student> students;
    private Student student;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private StudentDaoImpl studentDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(studentDao, "jdbcTemplate",
                jdbcTemplate);
        student = new Student.Builder<>().withStaffId((long) 11)
                .withStartDate(LocalDate.of(2017, 1, 13))
                .withState(StudentState.ACTIVE).build();
        students = List.of(student);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingStudent() {
        when(jdbcTemplate.update(eq(StudentDaoImpl.CREATE_STUDENT), anyMap())).
                thenReturn(1);

        int actual = studentDao.create(student);

        verify(jdbcTemplate).update(eq(StudentDaoImpl.CREATE_STUDENT), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingStudent() {
        when(jdbcTemplate.update(eq(StudentDaoImpl.CREATE_STUDENT), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                StudentDaoImpl.SQL_CREATE_STUDENT_ERROR.replace("{}", "%s"), 
                student.getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDao.create(student));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(StudentDaoImpl.CREATE_STUDENT), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingStudentById() {
        when(jdbcTemplate.update(eq(StudentDaoImpl.DELETE_STUDENT_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = studentDao.delete(id);

        verify(jdbcTemplate).update(eq(StudentDaoImpl.DELETE_STUDENT_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingStudentById() {
        when(jdbcTemplate.update(eq(StudentDaoImpl.DELETE_STUDENT_BY_ID), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                StudentDaoImpl.SQL_DELETE_STUDENT_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(StudentDaoImpl.DELETE_STUDENT_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingStudent() {
        when(jdbcTemplate.queryForObject(eq(StudentDaoImpl.FIND_BY_ID), anyMap(),
                any(StudentMapper.class))).thenReturn(student);

        Student returnStudent = studentDao.findById(id);

        verify(jdbcTemplate).queryForObject(eq(StudentDaoImpl.FIND_BY_ID), 
                anyMap(), any(StudentMapper.class));
        assertNotNull(returnStudent);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingStudent() {
        when(jdbcTemplate.queryForObject(eq(StudentDaoImpl.FIND_BY_ID), anyMap(),
                any(StudentMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                StudentDaoImpl.SQL_FIND_STUDENT_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(StudentDaoImpl.FIND_BY_ID), 
                anyMap(), any(StudentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllStudents() {
        when(jdbcTemplate.query(eq(StudentDaoImpl.FIND_ALL), 
                any(StudentMapper.class))).thenReturn(students);

        int actual = studentDao.findAll().size();

        verify(jdbcTemplate).query(eq(StudentDaoImpl.FIND_ALL), 
                any(StudentMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllStudents() {
        when(jdbcTemplate.query(eq(StudentDaoImpl.FIND_ALL), 
                any(StudentMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = StudentDaoImpl.SQL_FIND_ALL_STUDENTS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> studentDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(StudentDaoImpl.FIND_ALL), 
                any(StudentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

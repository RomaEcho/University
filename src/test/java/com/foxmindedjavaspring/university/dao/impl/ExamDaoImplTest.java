package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.dao.impl.ExamDaoImpl.ExamMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Exam;

class ExamDaoImplTest {
    private static final String SPLITTER = ":";
    private static final int COMPARED_PART = 2;
    private static final int expected = 1;
    private static final int id = 111;
    private List<Exam> exams;
    private Exam exam;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private ExamDaoImpl examDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(examDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        exam = new Exam("title");
        exams = List.of(exam);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingExam() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examDaoImpl.create(exam);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingExam() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamDaoImpl.SQL_CREATE_EXAM_ERROR.replace("{}", "%s"), 
                exam.getTitle());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.create(exam));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingExamById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examDaoImpl.delete(id);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingExamById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamDaoImpl.SQL_DELETE_EXAM_BY_ID_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingExam() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examDaoImpl.delete(exam);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingExam() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamDaoImpl.SQL_DELETE_EXAM_ERROR.replace("{}", "%s"), 
                exam.getTitle());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.delete(exam));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingExam() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(ExamMapper.class))).thenReturn(exam);

        Exam returnExam = examDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(ExamMapper.class));
        assertNotNull(returnExam);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingExam() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(ExamMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamDaoImpl.SQL_FIND_EXAM_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(ExamMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllExams() {
        when(jdbcTemplate.query(anyString(), any(ExamMapper.class)))
                .thenReturn(exams);

        int actual = examDaoImpl.findAll().size();

        verify(jdbcTemplate).query(anyString(), any(ExamMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllExams() {
        when(jdbcTemplate.query(anyString(), any(ExamMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = ExamDaoImpl.SQL_FIND_ALL_EXAMS_ERROR;

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(anyString(), any(ExamMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

}

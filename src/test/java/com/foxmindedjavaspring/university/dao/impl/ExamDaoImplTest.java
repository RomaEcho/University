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

import java.util.ArrayList;
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
import com.foxmindedjavaspring.university.utils.Utils;

class ExamDaoImplTest {
    private List<Exam> exams;
    private Exam exam;
    private int expected;
    private int actual;
    private int id;
    @Mock
    private Utils utils;
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
        id = 111;
        expected = 1;
        exams = new ArrayList<>();
        exams.add(exam);
    }

    @Test
    void shouldVerifyReturnValue_whileCreatingExam() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = examDaoImpl.create(exam);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileCreatingExam() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.create(exam));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamDaoImpl.SQL_CREATE_EXAM_ERROR));
        assertTrue(actualMessage.contains(exam.getTitle()));
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingExam() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = examDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileDeletingExam() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamDaoImpl.SQL_DELETE_EXAM_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingExam() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(ExamMapper.class))).thenReturn(exam);

        Exam returnExam = examDaoImpl.findById(id);

        assertNotNull(returnExam);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingExam() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .queryForObject(anyString(), anyMap(),
                        any(ExamMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamDaoImpl.SQL_FIND_EXAM_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingAllExams() {
        when(jdbcTemplate.query(anyString(), any(ExamMapper.class)))
                .thenReturn(exams);

        int actual = examDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingAllExams() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .query(anyString(), any(ExamMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamDaoImpl.SQL_FIND_ALL_EXAMS_ERROR));
    }

}

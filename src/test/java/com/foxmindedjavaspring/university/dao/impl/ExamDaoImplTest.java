package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
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
    private static final int expected = 1;
    private static final Long id = (long) 111;
    private List<Exam> exams;
    private Exam exam;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private ExamDaoImpl examDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(examDao, "jdbcTemplate",
                jdbcTemplate);
        exam = new Exam("title");
        exams = List.of(exam);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingExam() {
        when(jdbcTemplate.update(eq(ExamDaoImpl.CREATE_EXAM), anyMap())).
                thenReturn(1);

        int actual = examDao.create(exam);

        verify(jdbcTemplate).update(eq(ExamDaoImpl.CREATE_EXAM), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingExam() {
        when(jdbcTemplate.update(eq(ExamDaoImpl.CREATE_EXAM), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamDaoImpl.SQL_CREATE_EXAM_ERROR.replace("{}", "%s"), 
                exam.getTitle());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDao.create(exam));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(ExamDaoImpl.CREATE_EXAM), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingExamById() {
        when(jdbcTemplate.update(eq(ExamDaoImpl.DELETE_EXAM_BY_ID), anyMap())).
                thenReturn(1);

        int actual = examDao.delete(id);

        verify(jdbcTemplate).update(eq(ExamDaoImpl.DELETE_EXAM_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingExamById() {
        when(jdbcTemplate.update(eq(ExamDaoImpl.DELETE_EXAM_BY_ID), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamDaoImpl.SQL_DELETE_EXAM_BY_ID_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(ExamDaoImpl.DELETE_EXAM_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingExam() {
        when(jdbcTemplate.queryForObject(eq(ExamDaoImpl.FIND_BY_ID), anyMap(),
                any(ExamMapper.class))).thenReturn(exam);

        Exam returnExam = examDao.findById(id);

        verify(jdbcTemplate).queryForObject(eq(ExamDaoImpl.FIND_BY_ID), anyMap(),
                any(ExamMapper.class));
        assertNotNull(returnExam);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingExam() {
        when(jdbcTemplate.queryForObject(eq(ExamDaoImpl.FIND_BY_ID), anyMap(),
                any(ExamMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamDaoImpl.SQL_FIND_EXAM_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(ExamDaoImpl.FIND_BY_ID), 
                anyMap(), any(ExamMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllExams() {
        when(jdbcTemplate.query(eq(ExamDaoImpl.FIND_ALL), 
                any(ExamMapper.class))).thenReturn(exams);

        int actual = examDao.findAll().size();

        verify(jdbcTemplate).query(eq(ExamDaoImpl.FIND_ALL), 
                any(ExamMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllExams() {
        when(jdbcTemplate.query(eq(ExamDaoImpl.FIND_ALL), 
                any(ExamMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = ExamDaoImpl.SQL_FIND_ALL_EXAMS_ERROR;

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(ExamDaoImpl.FIND_ALL), 
                any(ExamMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

}

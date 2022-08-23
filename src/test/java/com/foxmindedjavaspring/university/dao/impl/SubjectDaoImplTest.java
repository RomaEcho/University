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

import com.foxmindedjavaspring.university.dao.impl.SubjectDaoImpl.SubjectMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.utils.Utils;

class SubjectDaoImplTest {
    private Subject subject;
    private List<Subject> subjects;
    private int expected;
    private int actual;
    private int id;
    @Mock
    private Utils utils;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private SubjectDaoImpl subjectDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(subjectDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        subject = new Subject(222, "name");
        subject.setDescription("description");
        id = 111;
        expected = 1;
        subjects = new ArrayList<>();
        subjects.add(subject);
    }

    @Test
    void shouldVerifyReturnValue_whileCreatingSubject() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = subjectDaoImpl.create(subject);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileCreatingSubject() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.create(subject));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_CREATE_SUBJECT_ERROR));
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingSubject() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = subjectDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileDeletingSubject() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_DELETE_SUBJECT_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingSubject() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(SubjectMapper.class)))
                .thenReturn(subject);

        Subject returnSubject = subjectDaoImpl.findById(id);

        assertNotNull(returnSubject);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingSubject() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .queryForObject(anyString(), anyMap(),
                        any(SubjectMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_FIND_SUBJECT_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingAllSubjects() {
        when(jdbcTemplate.query(anyString(), any(SubjectMapper.class)))
                .thenReturn(subjects);

        int actual = subjectDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingAllSubjects() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .query(anyString(), any(SubjectMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_FIND_ALL_SUBJECTS_ERROR));
    }
}

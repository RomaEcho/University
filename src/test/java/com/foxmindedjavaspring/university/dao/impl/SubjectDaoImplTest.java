package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

class SubjectDaoImplTest {
    private static final String SPLITTER = ":";
    private static final int COMPARED_PART = 2;
    private static final int expected = 1;
    private static final int id = 111;
    private List<Subject> subjects;
    private Subject subject;
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
        subjects = List.of(subject);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingSubject() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = subjectDaoImpl.create(subject);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingSubject() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.create(subject));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_CREATE_SUBJECT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingSubjectById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = subjectDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingSubjectById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_DELETE_SUBJECT_BY_ID_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingSubject() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = subjectDaoImpl.delete(subject);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingSubject() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.delete(subject));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_DELETE_SUBJECT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(subject
                .getNumber())));
        assertTrue(actualMessage.contains(subject.getName()));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingSubject() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(SubjectMapper.class))).thenReturn(subject);

        Subject returnSubject = subjectDaoImpl.findById(id);

        assertNotNull(returnSubject);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingSubject() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(SubjectMapper.class))).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(SubjectDaoImpl.SQL_FIND_SUBJECT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllSubjects() {
        when(jdbcTemplate.query(anyString(), any(SubjectMapper.class)))
                .thenReturn(subjects);

        int actual = subjectDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllSubjects() {
        when(jdbcTemplate.query(anyString(), any(SubjectMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> subjectDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(SubjectDaoImpl.SQL_FIND_ALL_SUBJECTS_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }
}

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

import com.foxmindedjavaspring.university.dao.impl.SubjectDaoImpl.SubjectMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Subject;

class SubjectDaoImplTest {
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
        when(jdbcTemplate.update(eq(SubjectDaoImpl.CREATE_SUBJECT), 
                anyMap())).thenReturn(1);

        int actual = subjectDaoImpl.create(subject);

        verify(jdbcTemplate).update(eq(SubjectDaoImpl.CREATE_SUBJECT), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingSubject() {
        when(jdbcTemplate.update(eq(SubjectDaoImpl.CREATE_SUBJECT), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                SubjectDaoImpl.SQL_CREATE_SUBJECT_ERROR.replace("{}", "%s"), 
                subject.getNumber());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> subjectDaoImpl.create(subject));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(SubjectDaoImpl.CREATE_SUBJECT), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingSubjectById() {
        when(jdbcTemplate.update(eq(SubjectDaoImpl.DELETE_SUBJECT_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = subjectDaoImpl.delete(id);

        verify(jdbcTemplate).update(eq(SubjectDaoImpl.DELETE_SUBJECT_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingSubjectById() {
        when(jdbcTemplate.update(eq(SubjectDaoImpl.DELETE_SUBJECT_BY_ID), 
                anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                SubjectDaoImpl.SQL_DELETE_SUBJECT_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> subjectDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(SubjectDaoImpl.DELETE_SUBJECT_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingSubject() {
        when(jdbcTemplate.queryForObject(eq(SubjectDaoImpl.FIND_BY_ID), 
                anyMap(), any(SubjectMapper.class))).thenReturn(subject);

        Subject returnSubject = subjectDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(eq(SubjectDaoImpl.FIND_BY_ID), 
                anyMap(), any(SubjectMapper.class));
        assertNotNull(returnSubject);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingSubject() {
        when(jdbcTemplate.queryForObject(eq(SubjectDaoImpl.FIND_BY_ID), 
                anyMap(), any(SubjectMapper.class))).
                thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                SubjectDaoImpl.SQL_FIND_SUBJECT_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> subjectDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(SubjectDaoImpl.FIND_BY_ID), 
                anyMap(), any(SubjectMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllSubjects() {
        when(jdbcTemplate.query(eq(SubjectDaoImpl.FIND_ALL), 
                any(SubjectMapper.class))).thenReturn(subjects);

        int actual = subjectDaoImpl.findAll().size();

        verify(jdbcTemplate).query(eq(SubjectDaoImpl.FIND_ALL), 
                any(SubjectMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllSubjects() {
        when(jdbcTemplate.query(eq(SubjectDaoImpl.FIND_ALL), 
                any(SubjectMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = SubjectDaoImpl.SQL_FIND_ALL_SUBJECTS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> subjectDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(SubjectDaoImpl.FIND_ALL), 
                any(SubjectMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

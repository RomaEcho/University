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

import com.foxmindedjavaspring.university.dao.impl.UniversityDaoImpl.UniversityMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.University;

class UniversityDaoImplTest {
    private static final String SPLITTER = ":";
    private static final int COMPARED_PART = 2;
    private static final int id = 111;
    private static final int expected = 1;
    private List<University> universities;
    private University university;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private UniversityDaoImpl universityDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(universityDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        university = new University("name", "hqLocation");
        universities = List.of(university);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingUniversity() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = universityDaoImpl.create(university);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingUniversity() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityDaoImpl.SQL_CREATE_UNIVERSITY_ERROR.
                        replace("{}", "%s"), university.getName());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDaoImpl.create(university));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingUniversityById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = universityDaoImpl.delete(id);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingUniversityById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityDaoImpl.SQL_DELETE_UNIVERSITY_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingUniversity() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = universityDaoImpl.delete(university);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingUniversity() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityDaoImpl.SQL_DELETE_UNIVERSITY_ERROR.
                        replace("{}", "%s"), university.getName());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDaoImpl.delete(university));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingUniversity() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(UniversityMapper.class))).thenReturn(university);

        University returnUniversity = universityDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(UniversityMapper.class));
        assertNotNull(returnUniversity);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingUniversity() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(UniversityMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityDaoImpl.SQL_FIND_UNIVERSITY_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(UniversityMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllUniversities() {
        when(jdbcTemplate.query(anyString(), any(UniversityMapper.class)))
                .thenReturn(universities);

        int actual = universityDaoImpl.findAll().size();

        verify(jdbcTemplate).query(anyString(), any(UniversityMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllUniversities() {
        when(jdbcTemplate.query(anyString(), any(UniversityMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = UniversityDaoImpl.
                SQL_FIND_ALL_UNIVERSITIES_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(anyString(), any(UniversityMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

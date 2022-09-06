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

import com.foxmindedjavaspring.university.dao.impl.UniversityDaoImpl.UniversityMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.University;

class UniversityDaoImplTest {
    private static final Long id = (long) 111;
    private static final int expected = 1;
    private List<University> universities;
    private University university;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private UniversityDaoImpl universityDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(universityDao, "jdbcTemplate",
                jdbcTemplate);
        university = new University("name", "hqLocation");
        universities = List.of(university);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingUniversity() {
        when(jdbcTemplate.update(eq(UniversityDaoImpl.CREATE_UNIVERSITY), 
                anyMap())).thenReturn(1);

        int actual = universityDao.create(university);

        verify(jdbcTemplate).update(eq(UniversityDaoImpl.CREATE_UNIVERSITY), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingUniversity() {
        when(jdbcTemplate.update(eq(UniversityDaoImpl.CREATE_UNIVERSITY), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityDaoImpl.SQL_CREATE_UNIVERSITY_ERROR.
                        replace("{}", "%s"), university.getName());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDao.create(university));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(UniversityDaoImpl.CREATE_UNIVERSITY), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingUniversityById() {
        when(jdbcTemplate.update(eq(UniversityDaoImpl.DELETE_UNIVERSITY_BY_ID),
                anyMap())).thenReturn(1);

        int actual = universityDao.delete(id);

        verify(jdbcTemplate).update(eq(UniversityDaoImpl.
                DELETE_UNIVERSITY_BY_ID), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingUniversityById() {
        when(jdbcTemplate.update(eq(UniversityDaoImpl.DELETE_UNIVERSITY_BY_ID), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityDaoImpl.SQL_DELETE_UNIVERSITY_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(UniversityDaoImpl.
                DELETE_UNIVERSITY_BY_ID), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingUniversity() {
        when(jdbcTemplate.queryForObject(eq(UniversityDaoImpl.FIND_BY_ID), 
                anyMap(), any(UniversityMapper.class))).thenReturn(university);

        University returnUniversity = universityDao.findById(id);

        verify(jdbcTemplate).queryForObject(eq(UniversityDaoImpl.FIND_BY_ID), 
                anyMap(), any(UniversityMapper.class));
        assertNotNull(returnUniversity);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingUniversity() {
        when(jdbcTemplate.queryForObject(eq(UniversityDaoImpl.FIND_BY_ID), 
                anyMap(), any(UniversityMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityDaoImpl.SQL_FIND_UNIVERSITY_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(UniversityDaoImpl.FIND_BY_ID), 
                anyMap(), any(UniversityMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllUniversities() {
        when(jdbcTemplate.query(eq(UniversityDaoImpl.FIND_ALL), 
                any(UniversityMapper.class))).thenReturn(universities);

        int actual = universityDao.findAll().size();

        verify(jdbcTemplate).query(eq(UniversityDaoImpl.FIND_ALL), 
                any(UniversityMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllUniversities() {
        when(jdbcTemplate.query(eq(UniversityDaoImpl.FIND_ALL), 
                any(UniversityMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = UniversityDaoImpl.
                SQL_FIND_ALL_UNIVERSITIES_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(UniversityDaoImpl.FIND_ALL), 
                any(UniversityMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

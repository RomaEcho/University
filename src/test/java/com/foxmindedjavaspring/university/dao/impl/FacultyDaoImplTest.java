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

import com.foxmindedjavaspring.university.dao.impl.FacultyDaoImpl.FacultyMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.model.University;

class FacultyDaoImplTest {
    private static final int expected = 1;
    private static final int id = 111;
    private List<Faculty> faculties;
    private Faculty faculty;
    private University university;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Mock
    private FacultyMapper facultyMapper;
    @InjectMocks
    private FacultyDaoImpl facultyDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(facultyDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        university = new University("name", "hqLocation");
        faculty = new Faculty.Builder().withUniversity(university)
                .withDepartment("department").withAddress("address").build();
        faculties = List.of(faculty);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingFaculty() {
        when(jdbcTemplate.update(eq(FacultyDaoImpl.CREATE_FACULTY), anyMap())).
                thenReturn(1);

        int actual = facultyDaoImpl.create(faculty);

        verify(jdbcTemplate).update(eq(FacultyDaoImpl.CREATE_FACULTY), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingFaculty() {
        when(jdbcTemplate.update(eq(FacultyDaoImpl.CREATE_FACULTY), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FacultyDaoImpl.SQL_CREATE_FACULTY_ERROR.replace("{}", "%s"), 
                faculty.getDepartment());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.create(faculty));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(FacultyDaoImpl.CREATE_FACULTY), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingFacultyById() {
        when(jdbcTemplate.update(eq(FacultyDaoImpl.DELETE_FACULTY_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = facultyDaoImpl.delete(id);

        verify(jdbcTemplate).update(eq(FacultyDaoImpl.DELETE_FACULTY_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingFacultyById() {
        when(jdbcTemplate.update(eq(FacultyDaoImpl.DELETE_FACULTY_BY_ID), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FacultyDaoImpl.SQL_DELETE_FACULTY_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(FacultyDaoImpl.DELETE_FACULTY_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingFaculty() {
        when(jdbcTemplate.queryForObject(eq(FacultyDaoImpl.FIND_BY_ID), anyMap(),
                any(FacultyMapper.class))).thenReturn(faculty);

        Faculty returnfaculty = facultyDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(eq(FacultyDaoImpl.FIND_BY_ID), 
                anyMap(), any(FacultyMapper.class));
        assertNotNull(returnfaculty);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingFaculty() {
        when(jdbcTemplate.queryForObject(eq(FacultyDaoImpl.FIND_BY_ID), anyMap(),
                any(FacultyMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FacultyDaoImpl.SQL_FIND_FACULTY_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(FacultyDaoImpl.FIND_BY_ID), 
                anyMap(), any(FacultyMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllFaculties() {
        when(jdbcTemplate.query(eq(FacultyDaoImpl.FIND_ALL), any(FacultyMapper.
                class))).thenReturn(faculties);

        int actual = facultyDaoImpl.findAll().size();

        verify(jdbcTemplate).query(eq(FacultyDaoImpl.FIND_ALL), 
                any(FacultyMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllFaculties() {
        when(jdbcTemplate.query(eq(FacultyDaoImpl.FIND_ALL), 
                any(FacultyMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = FacultyDaoImpl.SQL_FIND_ALL_FACULTIES_ERROR;

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(FacultyDaoImpl.FIND_ALL), 
                any(FacultyMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

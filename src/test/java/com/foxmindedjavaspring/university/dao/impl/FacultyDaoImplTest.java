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
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = facultyDaoImpl.create(faculty);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingFaculty() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FacultyDaoImpl.SQL_CREATE_FACULTY_ERROR.replace("{}", "%s"), 
                faculty.getDepartment());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.create(faculty));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingFacultyById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = facultyDaoImpl.delete(id);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingFacultyById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FacultyDaoImpl.SQL_DELETE_FACULTY_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingFaculty() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = facultyDaoImpl.delete(faculty);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingFaculty() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FacultyDaoImpl.SQL_DELETE_FACULTY_ERROR.replace("{}", "%s"), 
                faculty.getUniversity().getName(),
                faculty.getDepartment());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.delete(faculty));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingFaculty() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(FacultyMapper.class))).thenReturn(faculty);

        Faculty returnfaculty = facultyDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(FacultyMapper.class));
        assertNotNull(returnfaculty);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingFaculty() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(FacultyMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FacultyDaoImpl.SQL_FIND_FACULTY_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(FacultyMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllFaculties() {
        when(jdbcTemplate.query(anyString(), any(FacultyMapper.class)))
                .thenReturn(faculties);

        int actual = facultyDaoImpl.findAll().size();

        verify(jdbcTemplate).query(anyString(), any(FacultyMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllFaculties() {
        when(jdbcTemplate.query(anyString(), any(FacultyMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = FacultyDaoImpl.SQL_FIND_ALL_FACULTIES_ERROR;

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(anyString(), any(FacultyMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

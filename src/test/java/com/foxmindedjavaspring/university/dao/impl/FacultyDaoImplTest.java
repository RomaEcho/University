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

import com.foxmindedjavaspring.university.dao.impl.FacultyDaoImpl.FacultyMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.utils.Utils;

class FacultyDaoImplTest {
    private Faculty faculty;
    private University university;
    private List<Faculty> faculties;
    private int expected;
    private int actual;
    private int id;
    @Mock
    private Utils utils;
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
        faculty = new Faculty.Builder()
                            .withUniversity(university)
                            .withDepartment("department")
                            .withAddress("address")
                            .build();
        id = 111;
        expected = 1;
        faculties = new ArrayList<>();
        faculties.add(faculty);
    }

    @Test
    void shouldVerifyReturnValue_whileCreatingFaculty() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = facultyDaoImpl.create(faculty);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileCreatingFaculty() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.create(faculty));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(FacultyDaoImpl.SQL_CREATE_FACULTY_ERROR));
        assertTrue(actualMessage.contains(faculty.getDepartment()));
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingFaculty() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = facultyDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileDeletingFaculty() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(FacultyDaoImpl.SQL_DELETE_FACULTY_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingFaculty() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(FacultyMapper.class)))
                .thenReturn(faculty);

        Faculty returnfaculty = facultyDaoImpl.findById(id);

        assertNotNull(returnfaculty);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingFaculty() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .queryForObject(anyString(), anyMap(),
                        any(FacultyMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(FacultyDaoImpl.SQL_FIND_FACULTY_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingAllFaculties() {
        when(jdbcTemplate.query(anyString(), any(FacultyMapper.class)))
                .thenReturn(faculties);

        int actual = facultyDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingAllFaculties() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .query(anyString(), any(FacultyMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> facultyDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(FacultyDaoImpl.SQL_FIND_ALL_FACULTIES_ERROR));
    }
}

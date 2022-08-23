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

import com.foxmindedjavaspring.university.dao.impl.LecturerDaoImpl.LecturerMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.utils.Utils;

class LecturerDaoImplTest {
    private Lecturer lecturer;
    private List<Lecturer> lecturers;
    private int expected;
    private int actual;
    private int id;
    @Mock
    private Utils utils;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private LecturerDaoImpl lecturerDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(lecturerDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        lecturer = new Lecturer.Builder<>()
                .withStaffId(11)
                .withLevel("level")
                .build();
        id = 111;
        expected = 1;
        lecturers = new ArrayList<>();
        lecturers.add(lecturer);
    }

    @Test
    void shouldVerifyReturnValue_whileCreatingLecturer() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = lecturerDaoImpl.create(lecturer);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileCreatingLecturer() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.create(lecturer));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_CREATE_LECTURER_ERROR));
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingLecturer() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = lecturerDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileDeletingLecturer() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_DELETE_LECTURER_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingLecturer() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(LecturerMapper.class)))
                .thenReturn(lecturer);

        Lecturer returnLecturer = lecturerDaoImpl.findById(id);

        assertNotNull(returnLecturer);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingLecturer() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .queryForObject(anyString(), anyMap(),
                        any(LecturerMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_FIND_LECTURER_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingAllLecturers() {
        when(jdbcTemplate.query(anyString(), any(LecturerMapper.class)))
                .thenReturn(lecturers);

        int actual = lecturerDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingAllLecturers() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .query(anyString(), any(LecturerMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_FIND_ALL_LECTURERS_ERROR));
    }
}

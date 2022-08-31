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

import com.foxmindedjavaspring.university.dao.impl.LecturerDaoImpl.LecturerMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Lecturer;

class LecturerDaoImplTest {
    private static final String SPLITTER = ":";
    private static final int COMPARED_PART = 2;
    private static final int expected = 1;
    private static final int id = 111;
    private List<Lecturer> lecturers;
    private Lecturer lecturer;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private LecturerDaoImpl lecturerDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(lecturerDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        lecturer = new Lecturer.Builder<>().withStaffId((long) 11)
                .withLevel("level").build();
        lecturers = List.of(lecturer);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingLecturer() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = lecturerDaoImpl.create(lecturer);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingLecturer() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.create(lecturer));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_CREATE_LECTURER_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingLecturerById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = lecturerDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingLecturerById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_DELETE_LECTURER_BY_ID_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingLecturer() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = lecturerDaoImpl.delete(lecturer);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingLecturer() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.delete(lecturer));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_DELETE_LECTURER_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(lecturer.getStaffId().toString()));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingLecturer() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(LecturerMapper.class))).thenReturn(lecturer);

        Lecturer returnLecturer = lecturerDaoImpl.findById(id);

        assertNotNull(returnLecturer);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingLecturer() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(LecturerMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_FIND_LECTURER_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllLecturers() {
        when(jdbcTemplate.query(anyString(), any(LecturerMapper.class)))
                .thenReturn(lecturers);

        int actual = lecturerDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllLecturers() {
        when(jdbcTemplate.query(anyString(), any(LecturerMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> lecturerDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(LecturerDaoImpl.SQL_FIND_ALL_LECTURERS_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }
}

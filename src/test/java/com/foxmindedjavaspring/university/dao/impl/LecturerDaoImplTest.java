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

import com.foxmindedjavaspring.university.dao.impl.LecturerDaoImpl.LecturerMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Lecturer;

class LecturerDaoImplTest {
    private static final int expected = 1;
    private static final Long id = (long) 111;
    private List<Lecturer> lecturers;
    private Lecturer lecturer;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private LecturerDaoImpl lecturerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(lecturerDao, "jdbcTemplate",
                jdbcTemplate);
        lecturer = new Lecturer.Builder<>().withStaffId((long) 11)
                .withLevel("level").build();
        lecturers = List.of(lecturer);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingLecturer() {
        when(jdbcTemplate.update(eq(LecturerDaoImpl.CREATE_LECTURER), 
                anyMap())).thenReturn(1);

        int actual = lecturerDao.create(lecturer);

        verify(jdbcTemplate).update(eq(LecturerDaoImpl.CREATE_LECTURER), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingLecturer() {
        when(jdbcTemplate.update(eq(LecturerDaoImpl.CREATE_LECTURER), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                LecturerDaoImpl.SQL_CREATE_LECTURER_ERROR.replace("{}", "%s"), 
                lecturer.getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> lecturerDao.create(lecturer));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(LecturerDaoImpl.CREATE_LECTURER), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingLecturerById() {
        when(jdbcTemplate.update(eq(LecturerDaoImpl.DELETE_LECTURER_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = lecturerDao.delete(id);

        verify(jdbcTemplate).update(eq(LecturerDaoImpl.DELETE_LECTURER_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingLecturerById() {
        when(jdbcTemplate.update(eq(LecturerDaoImpl.DELETE_LECTURER_BY_ID), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                LecturerDaoImpl.SQL_DELETE_LECTURER_BY_ID_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> lecturerDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(LecturerDaoImpl.DELETE_LECTURER_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingLecturer() {
        when(jdbcTemplate.queryForObject(eq(LecturerDaoImpl.FIND_BY_ID), 
                anyMap(), any(LecturerMapper.class))).thenReturn(lecturer);

        Lecturer returnLecturer = lecturerDao.findById(id);

        verify(jdbcTemplate).queryForObject(eq(LecturerDaoImpl.FIND_BY_ID), 
                anyMap(), any(LecturerMapper.class));
        assertNotNull(returnLecturer);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingLecturer() {
        when(jdbcTemplate.queryForObject(eq(LecturerDaoImpl.FIND_BY_ID), 
                anyMap(), any(LecturerMapper.class))).
                        thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                LecturerDaoImpl.SQL_FIND_LECTURER_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> lecturerDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(LecturerDaoImpl.FIND_BY_ID), 
                anyMap(), any(LecturerMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllLecturers() {
        when(jdbcTemplate.query(eq(LecturerDaoImpl.FIND_ALL), 
                any(LecturerMapper.class))).thenReturn(lecturers);

        int actual = lecturerDao.findAll().size();

        verify(jdbcTemplate).query(eq(LecturerDaoImpl.FIND_ALL), 
                any(LecturerMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllLecturers() {
        when(jdbcTemplate.query(eq(LecturerDaoImpl.FIND_ALL), 
                any(LecturerMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = LecturerDaoImpl.SQL_FIND_ALL_LECTURERS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> lecturerDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(LecturerDaoImpl.FIND_ALL), 
                any(LecturerMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

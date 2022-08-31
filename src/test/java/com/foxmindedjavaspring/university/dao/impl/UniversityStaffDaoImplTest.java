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

import com.foxmindedjavaspring.university.dao.impl.UniversityStaffDaoImpl.UniversityStaffMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.UniversityStaff;

class UniversityStaffDaoImplTest {
    private static final String SPLITTER = ":";
    private static final int COMPARED_PART = 2;
    private static final int id = 111;
    private static final int expected = 1;
    private List<UniversityStaff> universityStaffs;
    private UniversityStaff universityStaff;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private UniversityStaffDaoImpl universityStaffDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(universityStaffDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        universityStaff = new UniversityStaff.Builder<>()
                .withStaffId((long) 11).withFirstName("firstName")
                .withLastName("lastName").withAddress("address")
                .withTitle("title").build();
        universityStaffs = List.of(universityStaff);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingUniversityStaff() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = universityStaffDaoImpl.create(universityStaff);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingUniversityStaff() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.create(universityStaff));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(
                UniversityStaffDaoImpl.SQL_CREATE_UNIVERSITY_STAFF_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingUniversityStaffById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = universityStaffDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingUniversityStaffById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(
                UniversityStaffDaoImpl.SQL_DELETE_UNIVERSITY_STAFF_ERROR_BY_ID
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingUniversityStaff() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = universityStaffDaoImpl.delete(universityStaff);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingUniversityStaff() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.delete(universityStaff));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(
                UniversityStaffDaoImpl.SQL_DELETE_UNIVERSITY_STAFF_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(universityStaff.getStaffId()
                .toString()));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingUniversityStaff() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(UniversityStaffMapper.class)))
                .thenReturn(universityStaff);

        UniversityStaff returnUniversityStaff = universityStaffDaoImpl
                .findById(id);

        assertNotNull(returnUniversityStaff);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingUniversityStaff() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(UniversityStaffMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(
                UniversityStaffDaoImpl.SQL_FIND_UNIVERSITY_STAFF_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllUniversityStaffs() {
        when(jdbcTemplate.query(anyString(),
                any(UniversityStaffMapper.class)))
                .thenReturn(universityStaffs);

        int actual = universityStaffDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllUniversityStaffs() {
        when(jdbcTemplate.query(anyString(),
                any(UniversityStaffMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(
                UniversityStaffDaoImpl.SQL_FIND_ALL_UNIVERSITY_STAFF_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }
}

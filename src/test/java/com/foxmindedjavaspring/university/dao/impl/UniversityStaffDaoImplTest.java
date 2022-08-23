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

import com.foxmindedjavaspring.university.dao.impl.UniversityStaffDaoImpl.UniversityStaffMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.UniversityStaff;
import com.foxmindedjavaspring.university.utils.Utils;

class UniversityStaffDaoImplTest {
    private UniversityStaff universityStaff;
    private List<UniversityStaff> universityStaffs;
    private int expected;
    private int actual;
    private int id;
    @Mock
    private Utils utils;
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
                                        .withStaffId(11)
                                        .withFirstName("firstName")
                                        .withLastName("lastName")
                                        .withAddress("address")
                                        .withTitle("title")
                                        .build();
        id = 111;
        expected = 1;
        universityStaffs = new ArrayList<>();
        universityStaffs.add(universityStaff);
    }

    @Test
    void shouldVerifyReturnValue_whileCreatingUniversityStaff() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = universityStaffDaoImpl.create(universityStaff);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileCreatingUniversityStaff() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.create(universityStaff));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(UniversityStaffDaoImpl.
                        SQL_CREATE_UNIVERSITY_STAFF_ERROR));
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingUniversityStaff() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        actual = universityStaffDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileDeletingUniversityStaff() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .update(anyString(), anyMap());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(UniversityStaffDaoImpl.
                        SQL_DELETE_UNIVERSITY_STAFF_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingUniversityStaff() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(UniversityStaffMapper.class)))
                .thenReturn(universityStaff);

        UniversityStaff returnUniversityStaff = 
                universityStaffDaoImpl.findById(id);

        assertNotNull(returnUniversityStaff);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingUniversityStaff() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .queryForObject(anyString(), anyMap(),
                        any(UniversityStaffMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(UniversityStaffDaoImpl.
                        SQL_FIND_UNIVERSITY_STAFF_ERROR));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValue_whileSearchingAllUniversityStaffs() {
        when(jdbcTemplate.query(anyString(), any(UniversityStaffMapper.class)))
                .thenReturn(universityStaffs);

        int actual = universityStaffDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrow_whileSearchingAllUniversityStaffs() {
        doThrow(RuntimeException.class).when(jdbcTemplate)
                .query(anyString(), any(UniversityStaffMapper.class));

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> universityStaffDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(UniversityStaffDaoImpl.
                        SQL_FIND_ALL_UNIVERSITY_STAFF_ERROR));
    }
}

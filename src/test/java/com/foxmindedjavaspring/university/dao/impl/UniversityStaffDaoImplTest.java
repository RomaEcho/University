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

import com.foxmindedjavaspring.university.dao.impl.UniversityStaffDaoImpl.UniversityStaffMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.UniversityStaff;

class UniversityStaffDaoImplTest {
    private static final Long id = (long) 111;
    private static final int expected = 1;
    private List<UniversityStaff> universityStaffs;
    private UniversityStaff universityStaff;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private UniversityStaffDaoImpl universityStaffDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(universityStaffDao, "jdbcTemplate",
                jdbcTemplate);
        universityStaff = new UniversityStaff.Builder<>()
                .withStaffId((long) 11).withFirstName("firstName")
                .withLastName("lastName").withAddress("address")
                .withTitle("title").build();
        universityStaffs = List.of(universityStaff);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingUniversityStaff() {
        when(jdbcTemplate.update(eq(UniversityStaffDaoImpl.
                CREATE_UNIVERSITY_STAFF), anyMap())).thenReturn(1);

        int actual = universityStaffDao.create(universityStaff);

        verify(jdbcTemplate).update(eq(UniversityStaffDaoImpl.
                CREATE_UNIVERSITY_STAFF), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingUniversityStaff() {
        when(jdbcTemplate.update(eq(UniversityStaffDaoImpl.
                CREATE_UNIVERSITY_STAFF), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityStaffDaoImpl.SQL_CREATE_UNIVERSITY_STAFF_ERROR.
                        replace("{}", "%s"), universityStaff.getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityStaffDao.create(universityStaff));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(UniversityStaffDaoImpl.
                CREATE_UNIVERSITY_STAFF), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingUniversityStaffById() {
        when(jdbcTemplate.update(eq(UniversityStaffDaoImpl.
                DELETE_UNIVERSITY_STAFF_BY_ID), anyMap())).thenReturn(1);

        int actual = universityStaffDao.delete(id);

        verify(jdbcTemplate).update(eq(UniversityStaffDaoImpl.
                DELETE_UNIVERSITY_STAFF_BY_ID), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingUniversityStaffById() {
        when(jdbcTemplate.update(eq(UniversityStaffDaoImpl.
                DELETE_UNIVERSITY_STAFF_BY_ID), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityStaffDaoImpl.SQL_DELETE_UNIVERSITY_STAFF_ERROR_BY_ID.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityStaffDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(UniversityStaffDaoImpl.
                DELETE_UNIVERSITY_STAFF_BY_ID), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingUniversityStaff() {
        when(jdbcTemplate.queryForObject(eq(UniversityStaffDaoImpl.FIND_BY_ID), anyMap(),
                any(UniversityStaffMapper.class))).thenReturn(universityStaff);

        UniversityStaff returnUniversityStaff = universityStaffDao
                .findById(id);

        verify(jdbcTemplate).queryForObject(eq(UniversityStaffDaoImpl.
                FIND_BY_ID), anyMap(), any(UniversityStaffMapper.class));
        assertNotNull(returnUniversityStaff);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingUniversityStaff() {
        when(jdbcTemplate.queryForObject(eq(UniversityStaffDaoImpl.FIND_BY_ID), anyMap(),
                any(UniversityStaffMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                UniversityStaffDaoImpl.SQL_FIND_UNIVERSITY_STAFF_ERROR.
                        replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityStaffDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(UniversityStaffDaoImpl.
                FIND_BY_ID), anyMap(), any(UniversityStaffMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllUniversityStaffs() {
        when(jdbcTemplate.query(eq(UniversityStaffDaoImpl.FIND_ALL),
                any(UniversityStaffMapper.class)))
                .thenReturn(universityStaffs);

        int actual = universityStaffDao.findAll().size();

        verify(jdbcTemplate).query(eq(UniversityStaffDaoImpl.FIND_ALL), 
                any(UniversityStaffMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllUniversityStaff() {
        when(jdbcTemplate.query(eq(UniversityStaffDaoImpl.FIND_ALL),
                any(UniversityStaffMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = UniversityStaffDaoImpl.
                SQL_FIND_ALL_UNIVERSITY_STAFF_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> universityStaffDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(UniversityStaffDaoImpl.FIND_ALL), 
                any(UniversityStaffMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

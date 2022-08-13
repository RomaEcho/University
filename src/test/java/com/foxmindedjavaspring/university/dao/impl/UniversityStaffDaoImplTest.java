package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.model.UniversityStaff;

class UniversityStaffDaoImplTest {
    private UniversityStaff universityStaff;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private UniversityStaffDaoImpl universityStaffDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(universityStaffDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        universityStaff = new UniversityStaff.Builder<>()
                                             .withStaffId("staffId")
                                             .withFirstName("firstName")
                                             .withLastName("lastName")
                                             .withAddress("address")
                                             .withTitle("title")
                                             .build();
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingUniversityStaff() {
        when(jdbcTemplate.update(
                UniversityStaffDaoImpl.REMOVE_UNIVERSITY_STAFF,
                universityStaff.getStaffId())).thenReturn(1);
        assertTrue(universityStaffDaoImpl.delete(universityStaff));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingUniversityStaff() {
        when(jdbcTemplate.update(UniversityStaffDaoImpl.ADD_UNIVERSITY_STAFF,
                universityStaff.getStaffId(), universityStaff.getFirstName(),
                universityStaff.getLastName(), universityStaff.getAddress(),
                universityStaff.getTitle())).thenReturn(1);
        assertTrue(universityStaffDaoImpl.create(universityStaff));
    }
}

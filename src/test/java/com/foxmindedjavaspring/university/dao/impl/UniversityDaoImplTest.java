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

import com.foxmindedjavaspring.university.model.University;

class UniversityDaoImplTest {
    private University university;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private UniversityDaoImpl universityDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(universityDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        university = new University("name", "hqLocation");
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingUniversity() {
        when(jdbcTemplate.update(UniversityDaoImpl.REMOVE_UNIVERSITY,
                university.getName(), university.getHqLocation()))
                .thenReturn(1);
        assertTrue(universityDaoImpl.delete(university));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingUniversity() {
        when(jdbcTemplate.update(UniversityDaoImpl.ADD_UNIVERSITY,
                university.getName(), university.getHqLocation()))
                .thenReturn(1);
        assertTrue(universityDaoImpl.create(university));
    }
}

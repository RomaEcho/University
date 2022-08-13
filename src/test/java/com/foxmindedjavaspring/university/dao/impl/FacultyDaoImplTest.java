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

import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.model.University;

class FacultyDaoImplTest {
    private Faculty faculty;
    private University university;
    @Mock
    private JdbcTemplate jdbcTemplate;
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
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingFaculty() {
        when(jdbcTemplate.update(FacultyDaoImpl.REMOVE_FACULTY,
                faculty.getDepartment(), faculty.getAddress()))
                .thenReturn(1);
        assertTrue(facultyDaoImpl.delete(faculty));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingFaculty() {
        when(jdbcTemplate.update(FacultyDaoImpl.ADD_FACULTY,
                faculty.getUniversity().getName(), faculty.getDepartment(),
                faculty.getAddress())).thenReturn(1);
        assertTrue(facultyDaoImpl.create(faculty));
    }
}

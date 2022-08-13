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

import com.foxmindedjavaspring.university.model.Lecturer;

class LecturerDaoImplTest {
    private Lecturer lecturer;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private LecturerDaoImpl lecturerDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(lecturerDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        lecturer = new Lecturer.Builder<>()
                               .withStaffId("staffId")
                               .withLevel("level")
                               .build();
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingLecturer() {
        when(jdbcTemplate.update(LecturerDaoImpl.REMOVE_LECTURER,
                lecturer.getStaffId(), lecturer.getLevel())).thenReturn(1);
        assertTrue(lecturerDaoImpl.delete(lecturer));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingLecturer() {
        when(jdbcTemplate.update(LecturerDaoImpl.ADD_LECTURER,
                lecturer.getStaffId(), lecturer.getLevel())).thenReturn(1);
        assertTrue(lecturerDaoImpl.create(lecturer));
    }

    @Test
    void shouldVerifyReturnValue_whileUpdatingLevel() {
        String level = "level";
        when(jdbcTemplate.update(LecturerDaoImpl.UPDATE_LEVEL, level,
                lecturer.getStaffId())).thenReturn(1);
        assertTrue(lecturerDaoImpl.updateLevel(lecturer, level));
    }
}

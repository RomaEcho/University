package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;

class StudentDaoImplTest {
    private Student student;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private StudentDaoImpl studentDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(studentDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        student = new Student.Builder<>()
                             .withStaffId("staffId")
                             .withStartDate(LocalDate.of(2017, 1, 13))
                             .withState(StudentState.ACTIVE)
                             .build();
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingStudent() {
        when(jdbcTemplate.update(StudentDaoImpl.REMOVE_STUDENT,
                student.getStaffId())).thenReturn(1);
        assertTrue(studentDaoImpl.delete(student));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingStudent() {
        when(jdbcTemplate.update(StudentDaoImpl.ADD_STUDENT,
                student.getStaffId(), student.getStartDate(),
                student.getState())).thenReturn(1);
        assertTrue(studentDaoImpl.create(student));
    }

    @Test
    void shouldVerifyReturnValue_whileSettingState() {
        when(jdbcTemplate.update(StudentDaoImpl.SET_STATE,
                StudentState.TERMINATED, student.getStaffId()))
                .thenReturn(1);
        assertTrue(studentDaoImpl.setState(student, StudentState.TERMINATED));
    }
}

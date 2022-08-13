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

import com.foxmindedjavaspring.university.model.Exam;

class ExamDaoImplTest {
    private Exam exam;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ExamDaoImpl examDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(examDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        exam = new Exam("title");
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingExam() {
        when(jdbcTemplate.update(ExamDaoImpl.REMOVE_EXAM, exam.getTitle()))
                .thenReturn(1);
        assertTrue(examDaoImpl.delete(exam));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingExam() {
        when(jdbcTemplate.update(ExamDaoImpl.ADD_EXAM, exam.getTitle()))
                .thenReturn(1);
        assertTrue(examDaoImpl.create(exam));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingRate() {
        String description = "description";
        when(jdbcTemplate.update(ExamDaoImpl.ADD_DESCRIPTION, description,
                exam.getTitle())).thenReturn(1);
        assertTrue(examDaoImpl.addDescription(exam, description));
    }
}

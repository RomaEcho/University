package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.model.Exam;

public class ExamServiceImplTest {
    private static final long id = 11;
    private Exam exam;
    @Mock
    private ExamDao examDao;
    @InjectMocks
    private ExamServiceImpl examService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exam = new Exam();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewExam() {
        examService.addExam(exam);

        verify(examDao).create(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExam() {
        examService.removeExam(exam);

        verify(examDao).delete(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingExam() {
        examService.getExam(id);

        verify(examDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExams() {
        examService.getAllExams();

        verify(examDao).findAll();
    }
}

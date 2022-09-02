package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Exam;

public class ExamServiceImplTest {
    private Exam exam;
    @Mock
    private GenericDao genericDao;
    @InjectMocks
    private ExamServiceImpl examServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exam = new Exam("title");
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewExam() {
        examServiceImpl.addExam(exam);

        verify(genericDao).create(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExam() {
        examServiceImpl.removeExam(exam);

        verify(genericDao).delete(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExams() {
        examServiceImpl.getAllExams();

        verify(genericDao).findAll();
    }
}

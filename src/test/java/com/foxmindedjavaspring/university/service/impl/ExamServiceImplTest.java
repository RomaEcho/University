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
    private static final long id = 11;
    private Exam exam;
    @Mock
    private GenericDao<Exam> genericDao;
    @InjectMocks
    private ExamServiceImpl examService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exam = new Exam("title");
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewExam() {
        examService.addExam(exam);

        verify(genericDao).create(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExam() {
        examService.removeExam(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingExam() {
        examService.getExam(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExams() {
        examService.getAllExams();

        verify(genericDao).findAll();
    }
}

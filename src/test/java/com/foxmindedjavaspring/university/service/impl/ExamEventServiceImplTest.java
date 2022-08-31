package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

public class ExamEventServiceImplTest {
    private ExamEvent examEvent;
    private Exam exam;
    @Mock
    private GenericDao genericDao;
    @InjectMocks
    private ExamEventServiceImpl examEventServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        exam = new Exam("title");
        examEvent = new ExamEvent.Builder()
                                 .withExam(exam)
                                 .withState(ExamState.UPCOMING)
                                 .withLab(22)
                                 .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewExamEvent() {
        examEventServiceImpl.add(examEvent);

        verify(genericDao).create(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExamEvent() {
        examEventServiceImpl.remove(examEvent);

        verify(genericDao).delete(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExamEvents() {
        examEventServiceImpl.getAll();

        verify(genericDao).findAll();
    }
}


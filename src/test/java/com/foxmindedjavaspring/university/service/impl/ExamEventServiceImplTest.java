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
    private static final long id = 11;
    private ExamEvent examEvent;
    private Exam exam;
    @Mock
    private GenericDao<ExamEvent> genericDao;
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
        examEventServiceImpl.addExamEvent(examEvent);

        verify(genericDao).create(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExamEvent() {
        examEventServiceImpl.removeExamEvent(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingExamEvent() {
        examEventServiceImpl.getExamEvent(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExamEvents() {
        examEventServiceImpl.getAllExamEvents();

        verify(genericDao).findAll();
    }
}


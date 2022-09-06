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
    private ExamEventServiceImpl examEventService;

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
        examEventService.addExamEvent(examEvent);

        verify(genericDao).create(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExamEvent() {
        examEventService.removeExamEvent(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingExamEvent() {
        examEventService.getExamEvent(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExamEvents() {
        examEventService.getAllExamEvents();

        verify(genericDao).findAll();
    }
}


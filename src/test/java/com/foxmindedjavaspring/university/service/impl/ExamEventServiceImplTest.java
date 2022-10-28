package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.ExamEventDao;
import com.foxmindedjavaspring.university.model.ExamEvent;

public class ExamEventServiceImplTest {
    private static final long id = 11;
    private ExamEvent examEvent;
    @Mock
    private ExamEventDao examEventDao;
    @InjectMocks
    private ExamEventServiceImpl examEventService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        examEvent = new ExamEvent();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewExamEvent() {
        examEventService.addExamEvent(examEvent);

        verify(examEventDao).create(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExamEvent() {
        examEventService.removeExamEvent(examEvent);

        verify(examEventDao).delete(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingExamEvent() {
        examEventService.getExamEvent(id);

        verify(examEventDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExamEvents() {
        examEventService.getAllExamEvents();

        verify(examEventDao).findAll();
    }
}


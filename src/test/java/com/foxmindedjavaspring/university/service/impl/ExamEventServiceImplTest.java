package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.ExamEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.ExamEvent;

import java.util.Optional;

public class ExamEventServiceImplTest {
    private static final long id = 11;
    private ExamEvent examEvent;
    @Mock
    private ExamEventRepository examEventRepository;
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

        verify(examEventRepository).save(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingExamEvent() {
        examEventService.editExamEvent(examEvent);

        verify(examEventRepository).save(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExamEvent() {
        examEventService.removeExamEvent(examEvent);

        verify(examEventRepository).delete(examEvent);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingExamEvent() {
        when(examEventRepository.findById(anyLong()))
                .thenReturn(Optional.of(examEvent));

        examEventService.getExamEvent(id);

        verify(examEventRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExamEvents() {
        examEventService.getAllExamEvents();

        verify(examEventRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingExamEvent() {
        when(examEventRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                ExamEventServiceImpl.GET_EXAM_EVENT_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> examEventService.getExamEvent(id));
        String actualMessage = exception.getMessage();

        verify(examEventRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}


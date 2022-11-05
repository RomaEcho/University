package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.ExamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Exam;

import java.util.Optional;

public class ExamServiceImplTest {
    private static final long id = 11;
    private Exam exam;
    @Mock
    private ExamRepository examRepository;
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

        verify(examRepository).save(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhilUpdatingExam() {
        examService.editExam(exam);

        verify(examRepository).save(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingExam() {
        examService.removeExam(exam);

        verify(examRepository).delete(exam);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingExam() {
        when(examRepository.findById(anyLong())).thenReturn(Optional.of(exam));

        examService.getExam(id);

        verify(examRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllExams() {
        examService.getAllExams();

        verify(examRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingExam() {
        when(examRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                ExamServiceImpl.GET_EXAM_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> examService.getExam(id));
        String actualMessage = exception.getMessage();

        verify(examRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}

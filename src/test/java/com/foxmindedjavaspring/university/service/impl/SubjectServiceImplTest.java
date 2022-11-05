package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Subject;

import java.util.Optional;

public class SubjectServiceImplTest {
    private static final long id = 11;
    private static final String name = "name";
    private Subject subject;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewSubject() {
        subjectService.addSubject(subject);

        verify(subjectRepository).save(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingSubject() {
        subjectService.editSubject(subject);

        verify(subjectRepository).save(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingSubject() {
        subjectService.removeSubject(subject);

        verify(subjectRepository).delete(subject);
    }
    
    @Test
    void shouldVerifyAllInvocationsWhileGettingSubject() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));

        subjectService.getSubject(id);

        verify(subjectRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllSubjects() {
        subjectService.getAllSubjects();

        verify(subjectRepository).findAll();
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingSubjectsWithNameContaining() {
        subjectService.getByName(name);

        verify(subjectRepository).findByName(name);
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingSubject() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                SubjectServiceImpl.GET_SUBJECT_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> subjectService.getSubject(id));
        String actualMessage = exception.getMessage();

        verify(subjectRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}

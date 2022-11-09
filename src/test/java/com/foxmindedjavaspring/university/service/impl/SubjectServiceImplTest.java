package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.mapper.SubjectMapper;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SubjectServiceImplTest {
    private static final long id = 11;
    private static final String name = "name";
    private Subject subject;
    private SubjectDto subjectDto;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private SubjectMapper subjectMapper;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject();
        subjectDto = new SubjectDto();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewSubject() {
        subjectService.addSubject(subject);

        verify(subjectRepository).save(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewSubjectUsingSubjectDto() {
        when(subjectMapper.apply(subjectDto)).thenReturn(subject);

        subjectService.addSubject(subjectDto);

        verify(subjectRepository).save(subject);
        verify(subjectMapper).apply(subjectDto);
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
    void shouldVerifyAllInvocationsWhileRemovingSubjectUsingSubjectDto() {
        when(subjectMapper.apply(subjectDto)).thenReturn(subject);

        subjectService.removeSubject(subjectDto);

        verify(subjectRepository).delete(subject);
        verify(subjectMapper).apply(subjectDto);
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

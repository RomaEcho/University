package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.SubjectDao;
import com.foxmindedjavaspring.university.model.Subject;

public class SubjectServiceImplTest {
    private static final long id = 11;
    private Subject subject;
    @Mock
    private SubjectDao subjectDao;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject((long) id, 222, "name");
        subject.setDescription("description");
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewSubject() {
        subjectService.addSubject(subject);

        verify(subjectDao).create(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingSubject() {
        subjectService.removeSubject(id);

        verify(subjectDao).delete(id);
    }
    
    @Test
    void shouldVerifyAllInvocationsWhileGettingSubject() {
        subjectService.getSubject(id);

        verify(subjectDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllSubjects() {
        subjectService.getAllSubjects();

        verify(subjectDao).findAll();
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingSubject() {
        subjectService.editSubject(id, subject);

        verify(subjectDao).update(anyLong(), any(Subject.class));
    }
}

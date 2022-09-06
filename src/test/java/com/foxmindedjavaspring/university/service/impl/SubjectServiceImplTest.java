package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Subject;

public class SubjectServiceImplTest {
    private static final long id = 11;
    private Subject subject;
    @Mock
    private GenericDao<Subject> genericDao;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject(222, "name");
        subject.setDescription("description");
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewSubject() {
        subjectService.addSubject(subject);

        verify(genericDao).create(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingSubject() {
        subjectService.removeSubject(id);

        verify(genericDao).delete(id);
    }
    
    @Test
    void shouldVerifyAllInvocationsWhileGettingSubject() {
        subjectService.getSubject(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllSubjects() {
        subjectService.getAllSubjects();

        verify(genericDao).findAll();
    }
}

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
    private Subject subject;
    @Mock
    private GenericDao genericDao;
    @InjectMocks
    private SubjectServiceImpl subjectServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject(222, "name");
        subject.setDescription("description");
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewSubject() {
        subjectServiceImpl.add(subject);

        verify(genericDao).create(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingSubject() {
        subjectServiceImpl.remove(subject);

        verify(genericDao).delete(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllSubjects() {
        subjectServiceImpl.getAll();

        verify(genericDao).findAll();
    }
}

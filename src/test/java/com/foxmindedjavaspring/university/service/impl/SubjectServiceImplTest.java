package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.impl.SubjectDaoImpl;
import com.foxmindedjavaspring.university.model.Subject;

public class SubjectServiceImplTest {
    private static final long id = 11;
    private static final String name = "name";
    private Subject subject;
    @Mock
    private SubjectDaoImpl subjectDao;
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

        verify(subjectDao).create(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingSubject() {
        subjectService.removeSubject(subject);

        verify(subjectDao).delete(subject);
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
        subjectService.editSubject(subject);

        verify(subjectDao).update(any(Subject.class));
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingSubjectsWithNameContaining() {
        subjectService.getByName(name);

        verify(subjectDao).findByName(name);
    }
}

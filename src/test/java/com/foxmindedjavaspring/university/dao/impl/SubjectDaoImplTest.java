package com.foxmindedjavaspring.university.dao.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Subject;

import java.util.List;

class SubjectDaoImplTest {
    private static final Long id = (long) 111;
    private static final String name = "name";
    private Subject subject;
    private List<Subject> subjects;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;
    @Mock
    private TypedQuery<Subject> typedQuery;
    @InjectMocks
    private SubjectDaoImpl subjectDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject();
        subjects = List.of(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileSearchingById() {
        subjectDao.findById(id);

        verify(entityManager).find(any(Class.class), anyLong());
    }

    @Test
    void shouldVerifyAllInvocationsWhileSearchingAll() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(subjects);

        subjectDao.findAll();

        verify(entityManager).createQuery(anyString());
        verify(query).getResultList();
    }

    @Test
    void shouldVerifyAllInvocationsWhileCreating() {
        subjectDao.create(subject);

        verify(entityManager).persist(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileDeleting() {
        when(entityManager.merge(subject)).thenReturn(subject);

        subjectDao.delete(subject);

        verify(entityManager).merge(subject);
        verify(entityManager).remove(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdating() {
        subjectDao.update(subject);

        verify(entityManager).merge(subject);
    }

    @Test
    void shouldVerifyAllInvocationsWhileSearchingInNames() {
        when(entityManager.createQuery(SubjectDaoImpl.FIND_BY_NAME,
                    Subject.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), eq(name)))
                .thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(subjects);

        subjectDao.findByName(name);

        verify(entityManager).createQuery(SubjectDaoImpl.FIND_BY_NAME,
                    Subject.class);
        verify(typedQuery).setParameter("name", name);
        verify(typedQuery).getResultList();
    }
}

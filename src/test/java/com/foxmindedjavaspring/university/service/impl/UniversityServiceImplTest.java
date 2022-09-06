package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.University;

public class UniversityServiceImplTest {
    private static final long id = 11;
    private University university;
    @Mock
    private GenericDao<University> genericDao;
    @InjectMocks
    private UniversityServiceImpl universityServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        university = new University("name", "hqLocation");
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewUniversity() {
        universityServiceImpl.addUniversity(university);

        verify(genericDao).create(university);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingUniversity() {
        universityServiceImpl.removeUniversity(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingUniversity() {
        universityServiceImpl.getUniversity(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllUniversities() {
        universityServiceImpl.getAllUniversities();

        verify(genericDao).findAll();
    }
}

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
    private University university;
    @Mock
    private GenericDao genericDao;
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
        universityServiceImpl.removeUniversity(university);

        verify(genericDao).delete(university);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllUniversities() {
        universityServiceImpl.getAllUniversities();

        verify(genericDao).findAll();
    }
}

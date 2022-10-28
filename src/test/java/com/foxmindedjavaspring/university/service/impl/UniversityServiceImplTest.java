package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.UniversityDao;
import com.foxmindedjavaspring.university.model.University;

public class UniversityServiceImplTest {
    private static final long id = 11;
    private University university;
    @Mock
    private UniversityDao universityDao;
    @InjectMocks
    private UniversityServiceImpl universityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        university = new University();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewUniversity() {
        universityService.addUniversity(university);

        verify(universityDao).create(university);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingUniversity() {
        universityService.removeUniversity(university);

        verify(universityDao).delete(university);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingUniversity() {
        universityService.getUniversity(id);

        verify(universityDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllUniversities() {
        universityService.getAllUniversities();

        verify(universityDao).findAll();
    }
}

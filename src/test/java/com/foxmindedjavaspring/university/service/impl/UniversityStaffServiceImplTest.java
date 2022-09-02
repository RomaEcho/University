package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.UniversityStaff;

public class UniversityStaffServiceImplTest {
    private UniversityStaff universityStaff;
    @Mock
    private GenericDao genericDao;
    @InjectMocks
    private UniversityStaffServiceImpl universityStaffServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        universityStaff = new UniversityStaff.Builder<>()
                                            .withStaffId((long) 11)
                                            .withFirstName("firstName")
                                            .withLastName("lastName")
                                            .withAddress("address")
                                            .withTitle("title")
                                            .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewUniversity() {
        universityStaffServiceImpl.addUniversityStaff(universityStaff);

        verify(genericDao).create(universityStaff);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingUniversity() {
        universityStaffServiceImpl.removeUniversityStaff(universityStaff);

        verify(genericDao).delete(universityStaff);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllUniversities() {
        universityStaffServiceImpl.getAllUniversityStaff();

        verify(genericDao).findAll();
    }
}

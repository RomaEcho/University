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
    private static final long id = 11;
    private UniversityStaff universityStaff;
    @Mock
    private GenericDao<UniversityStaff> genericDao;
    @InjectMocks
    private UniversityStaffServiceImpl universityStaffService;

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
    void shouldVerifyAllInvocationsWhileAddingNewUniversityStaff() {
        universityStaffService.addUniversityStaff(universityStaff);

        verify(genericDao).create(universityStaff);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingUniversityStaff() {
        universityStaffService.removeUniversityStaff(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingUniversityStaff() {
        universityStaffService.getUniversityStaff(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllUniversityStaff() {
        universityStaffService.getAllUniversityStaff();

        verify(genericDao).findAll();
    }
}

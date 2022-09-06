package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.model.University;

public class FacultyServiceImplTest {
    private static final long id = 11;
    private Faculty faculty;
    private University university;
    @Mock
    private GenericDao<Faculty> genericDao;
    @InjectMocks
    private FacultyServiceImpl facultyServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        university = new University("name", "hqLocation");
        faculty = new Faculty.Builder()
                             .withUniversity(university)
                             .withDepartment("department")
                             .withAddress("address")
                             .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewFaculty() {
        facultyServiceImpl.addFaculty(faculty);

        verify(genericDao).create(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFaculty() {
        facultyServiceImpl.removeFaculty(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingFaculty() {
        facultyServiceImpl.getFaculty(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllFaculties() {
        facultyServiceImpl.getAllFaculties();

        verify(genericDao).findAll();
    }
}

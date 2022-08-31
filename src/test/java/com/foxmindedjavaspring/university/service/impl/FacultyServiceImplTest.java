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
    private Faculty faculty;
    private University university;
    @Mock
    private GenericDao genericDao;
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
        facultyServiceImpl.add(faculty);

        verify(genericDao).create(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFaculty() {
        facultyServiceImpl.remove(faculty);

        verify(genericDao).delete(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllFaculties() {
        facultyServiceImpl.getAll();

        verify(genericDao).findAll();
    }
}

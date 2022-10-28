package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.FacultyDao;
import com.foxmindedjavaspring.university.model.Faculty;

public class FacultyServiceImplTest {
    private static final long id = 11;
    private Faculty faculty;
    @Mock
    private FacultyDao facultyDao;
    @InjectMocks
    private FacultyServiceImpl facultyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        faculty = new Faculty();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewFaculty() {
        facultyService.addFaculty(faculty);

        verify(facultyDao).create(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFaculty() {
        facultyService.removeFaculty(faculty);

        verify(facultyDao).delete(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingFaculty() {
        facultyService.getFaculty(id);

        verify(facultyDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllFaculties() {
        facultyService.getAllFaculties();

        verify(facultyDao).findAll();
    }
}

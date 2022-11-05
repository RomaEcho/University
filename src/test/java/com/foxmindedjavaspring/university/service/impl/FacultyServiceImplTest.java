package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Faculty;

import java.util.Optional;

public class FacultyServiceImplTest {
    private static final long id = 11;
    private Faculty faculty;
    @Mock
    private FacultyRepository facultyRepository;
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

        verify(facultyRepository).save(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingFaculty() {
        facultyService.editFaculty(faculty);

        verify(facultyRepository).save(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFaculty() {
        facultyService.removeFaculty(faculty);

        verify(facultyRepository).delete(faculty);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingFaculty() {
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));

        facultyService.getFaculty(id);

        verify(facultyRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllFaculties() {
        facultyService.getAllFaculties();

        verify(facultyRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingFaculty() {
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                FacultyServiceImpl.GET_FACULTY_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> facultyService.getFaculty(id));
        String actualMessage = exception.getMessage();

        verify(facultyRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}

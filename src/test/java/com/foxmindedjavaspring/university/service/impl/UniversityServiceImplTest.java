package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.UniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.University;

import java.util.Optional;

public class UniversityServiceImplTest {
    private static final long id = 11;
    private University university;
    @Mock
    private UniversityRepository universityRepository;
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

        verify(universityRepository).save(university);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingUniversity() {
        universityService.editUniversity(university);

        verify(universityRepository).save(university);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingUniversity() {
        universityService.removeUniversity(university);

        verify(universityRepository).delete(university);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingUniversity() {
        when(universityRepository.findById(anyLong()))
                .thenReturn(Optional.of(university));

        universityService.getUniversity(id);

        verify(universityRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllUniversities() {
        universityService.getAllUniversities();

        verify(universityRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingUniversity() {
        when(universityRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                UniversityServiceImpl.GET_UNIVERSITY_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> universityService.getUniversity(id));
        String actualMessage = exception.getMessage();

        verify(universityRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}

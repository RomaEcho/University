package com.foxmindedjavaspring.university.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.service.UniversityService;

class UniversityControllerTest {
    private University university;
    private List<University> universities;
    @Mock
    private UniversityService universityService;
    @Mock
    private Model model;
    @InjectMocks
    private UniversityController universityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        university = new University("name", "hqLocation");
        universities = List.of(university);
    }

    @Test
    void shouldVerifyControllerUniversityReturnValue() throws Exception { 
        when(universityService.getAllUniversities()).thenReturn(universities);
        String expected = "about";

        String actual = universityController.show(model);

        assertEquals(expected, actual);
    }
}

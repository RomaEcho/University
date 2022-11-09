package com.foxmindedjavaspring.university.controller;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SubjectControllerTest {
    private Subject subject;
    private SubjectDto subjectDto;
    private List<Subject> subjects;
    private static final Long id = (long) 111;
    @Mock
    private SubjectService subjectService;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @InjectMocks
    private SubjectController subjectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject();
        subject.setDescription("description");
        subjectDto = new SubjectDto();
        subjectDto.setId(id);
        subjects = List.of(subject);
    }

    @Test
    void shouldVerifyControllerIndexReturnValue() {
        when(subjectService.getAllSubjects()).thenReturn(subjects);
        String expected = "subjects/index";

        String actual = subjectController.showAll(subjectDto, model);

        verify(subjectService).getAllSubjects();
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerAddingFormReturnValue() {
        String expected = "subjects/add";

        String actual = subjectController.showFormForAdd(subjectDto);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerAddingSubjectReturnValue() {
        String expected = "redirect:/subjects";

        String actual = subjectController.addSubject(subjectDto, bindingResult);

        verify(subjectService).addSubject(any(SubjectDto.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerSearchingSubjectReturnValue() {
        when(subjectService.getSubject(anyLong())).thenReturn(subject);
        String name = "name";
        String expected = "subjects/result";

        String actual = subjectController.findByName(name, model);

        verify(subjectService).getByName(anyString());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerAboutReturnValue() {
        String expected = "subjects/result";

        String actual = subjectController.about(subjectDto, model);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerFormForUpdateReturnValue() {
        String expected = "subjects/update";

        String actual = subjectController.showFormForUpdate(subjectDto);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerUpdateSubjectReturnValue() {
        String expected = "redirect:/subjects";

        String actual = subjectController.updateSubject(subjectDto,
                bindingResult);

        verify(subjectService).addSubject(any(SubjectDto.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerDeleteSubjectReturnValue() {
        String expected = "redirect:/subjects";

        String actual = subjectController.delete(subjectDto);

        verify(subjectService).removeSubject(any(SubjectDto.class));
        assertEquals(expected, actual);
    }
}

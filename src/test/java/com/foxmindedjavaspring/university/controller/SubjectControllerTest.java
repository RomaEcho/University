package com.foxmindedjavaspring.university.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;

public class SubjectControllerTest {
    private Subject subject;
    private SubjectDto subjectDto;
    private List<Subject> subjects;
    private static final Long id = (long) 111;
    @Mock
    private SubjectService subjectService;
    @Mock
    private Model model;
    @InjectMocks
    private SubjectController subjectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = new Subject((long) 111, 222, "name");
        subject.setDescription("description");
        subjectDto = new SubjectDto();
        subjectDto.setId(id);
        subjects = List.of(subject);
    }

    @Test
    void shouldVerifyControllerIndexReturnValue() throws Exception { 
        when(subjectService.getAllSubjects()).thenReturn(subjects);
        String expected = "subjects/index";

        String actual = subjectController.showAll(subjectDto, model);

        verify(subjectService).getAllSubjects();
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerAddingFormReturnValue() throws Exception { 
        String expected = "subjects/add";

        String actual = subjectController.showFormForAdd(subjectDto);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerAddingSubjectReturnValue() throws Exception { 
        String expected = "redirect:/subjects";

        String actual = subjectController.addSubject(subjectDto);

        verify(subjectService).addSubject(any(Subject.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerSearchingSubjectReturnValue() throws Exception { 
        when(subjectService.getSubject(anyLong())).thenReturn(subject);
        String expected = "subjects/about";

        String actual = subjectController.find(subjectDto, model);

        verify(subjectService).getSubject(anyLong());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerAboutReturnValue() throws Exception { 
        String expected = "subjects/about";

        String actual = subjectController.about(model);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerFormForUpdateReturnValue() throws Exception { 
        String expected = "subjects/update";

        String actual = subjectController.showFormForUpdate(id, subjectDto);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerUpdateSubjectReturnValue() throws Exception { 
        String expected = "redirect:/subjects";

        String actual = subjectController.updateSubject(subjectDto);

        verify(subjectService).editSubject(anyLong(), any(Subject.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyControllerDeleteSubjectReturnValue() throws Exception { 
        String expected = "redirect:/subjects";

        String actual = subjectController.delete(id);

        verify(subjectService).removeSubject(anyLong());
        assertEquals(expected, actual);
    }
}

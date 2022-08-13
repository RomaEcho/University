package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.model.Subject;

class SubjectDaoImplTest {
    private Subject subject;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private SubjectDaoImpl subjectDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(subjectDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        subject = new Subject(222, "name");
        subject.setDescription("description");
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingSubject() {
        when(jdbcTemplate.update(SubjectDaoImpl.REMOVE_SUBJECT,
                subject.getNumber())).thenReturn(1);
        assertTrue(subjectDaoImpl.delete(subject));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingSubject() {
        when(jdbcTemplate.update(SubjectDaoImpl.ADD_SUBJECT,
                subject.getNumber(), subject.getName(),
                subject.getDescription())).thenReturn(1);
        assertTrue(subjectDaoImpl.create(subject));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingDescription() {
        String description = "description";
        when(jdbcTemplate.update(SubjectDaoImpl.ADD_DESCRIPTION, description,
                subject.getNumber())).thenReturn(1);
        assertTrue(subjectDaoImpl.addDescription(subject, description));
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

class ExamEventDaoImplTest {
    private Exam exam;
    private ExamEvent examEvent;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ExamEventDaoImpl examEventDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(examEventDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        exam = new Exam("title");
        examEvent = new ExamEvent.Builder()
                                 .withExam(exam)
                                 .withDate(LocalDate.of(2017, 1, 13))
                                 .withState(ExamState.UPCOMING)
                                 .withLab(22)
                                 .build();
    }

    @Test
    void shouldVerifyReturnValue_whileDeletingExamEvent() {
        when(jdbcTemplate.update(ExamEventDaoImpl.REMOVE_EXAM_EVENT,
                examEvent.getExam().getTitle(), examEvent.getDate(),
                examEvent.getLab())).thenReturn(1);
        assertTrue(examEventDaoImpl.delete(examEvent));
    }

    @Test
    void shouldVerifyReturnValue_whileAddingExamEvent() {
        when(jdbcTemplate.update(ExamEventDaoImpl.ADD_EXAM_EVENT,
                examEvent.getExam().getTitle(), examEvent.getDate(),
                examEvent.getState(), examEvent.getLab())).thenReturn(1);
        assertTrue(examEventDaoImpl.create(examEvent));
    }

    @Test
    void shouldVerifyReturnValue_whileSettingState() {
        when(jdbcTemplate.update(ExamEventDaoImpl.SET_STATE,
                ExamState.CLOSED, examEvent.getExam().getTitle(),
                examEvent.getDate(), examEvent.getLab())).thenReturn(1);
        assertTrue(examEventDaoImpl.setState(examEvent, ExamState.CLOSED));
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.dao.impl.ExamEventDaoImpl.ExamEventMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

class ExamEventDaoImplTest {
    private static final String SPLITTER = ":";
    private static final int COMPARED_PART = 2;
    private static final int expected = 1;
    private static final int id = 111;
    private List<ExamEvent> examEvents;
    private ExamEvent examEvent;
    private Exam exam;
    @Mock
    private ExamEventMapper examEventMapper;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @InjectMocks
    private ExamEventDaoImpl examEventDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(examEventDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        exam = new Exam("title");
        examEvent = new ExamEvent.Builder().withExam(exam)
                .withState(ExamState.UPCOMING).withLab(22).build();
        examEvents = List.of(examEvent);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examEventDaoImpl.create(examEvent);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examEventDaoImpl.create(examEvent));

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage
                .contains(ExamEventDaoImpl.SQL_CREATE_EXAM_EVENT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingExamEventById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examEventDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingExamEventById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examEventDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamEventDaoImpl.SQL_DELETE_EXAM_EVENT_BY_ID_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examEventDaoImpl.delete(examEvent);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examEventDaoImpl.delete(examEvent));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamEventDaoImpl.SQL_DELETE_EXAM_EVENT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(examEvent.getExam().getTitle()));
        assertTrue(actualMessage.contains(Integer.toString(examEvent.getLab())));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingExamEvent() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(ExamEventMapper.class))).thenReturn(examEvent);

        ExamEvent returnExamEvent = examEventDaoImpl.findById(id);

        assertNotNull(returnExamEvent);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingExamEvent() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(ExamEventMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examEventDaoImpl.findById(id));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamEventDaoImpl.SQL_FIND_EXAM_EVENT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllExamEvents() {
        when(jdbcTemplate.query(anyString(), any(ExamEventMapper.class)))
                .thenReturn(examEvents);

        int actual = examEventDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllExamEvents() {
        when(jdbcTemplate.query(anyString(), any(ExamEventMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examEventDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(ExamEventDaoImpl.SQL_FIND_ALL_EXAM_EVENTS_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }
}

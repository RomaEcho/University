package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
        examEvent = new ExamEvent.Builder()
                                 .withExam(exam)
                                 .withState(ExamState.UPCOMING)
                                 .withStartTime(LocalDateTime.now())
                                 .withEndTime(LocalDateTime.now())
                                 .withLab(22)
                                 .withRate(5)
                                 .build();
        examEvents = List.of(examEvent);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examEventDaoImpl.create(examEvent);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamEventDaoImpl.SQL_CREATE_EXAM_EVENT_ERROR.replace("{}", "%s"), 
                examEvent.getExam().getTitle());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> examEventDaoImpl.create(examEvent));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
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
        String expectedMessage = String.format(
                ExamEventDaoImpl.SQL_DELETE_EXAM_EVENT_BY_ID_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows( UniversityDataAcessException.class,
                () -> examEventDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = examEventDaoImpl.delete(examEvent);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingExamEvent() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamEventDaoImpl.SQL_DELETE_EXAM_EVENT_ERROR.replace("{}", "%s"), 
                examEvent.getExam().getTitle(),
                examEvent.getLab());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> examEventDaoImpl.delete(examEvent));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingExamEvent() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(ExamEventMapper.class))).thenReturn(examEvent);

        ExamEvent returnExamEvent = examEventDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(ExamEventMapper.class));
        assertNotNull(returnExamEvent);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingExamEvent() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(ExamEventMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                ExamEventDaoImpl.SQL_FIND_EXAM_EVENT_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> examEventDaoImpl.findById(id));

        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(ExamEventMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllExamEvents() {
        when(jdbcTemplate.query(anyString(), any(ExamEventMapper.class)))
                .thenReturn(examEvents);

        int actual = examEventDaoImpl.findAll().size();

        verify(jdbcTemplate).query(anyString(), any(ExamEventMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllExamEvents() {
        when(jdbcTemplate.query(anyString(), any(ExamEventMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = ExamEventDaoImpl.
                SQL_FIND_ALL_EXAM_EVENTS_ERROR;

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> examEventDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(anyString(), any(ExamEventMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

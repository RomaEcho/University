package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.foxmindedjavaspring.university.utils.Utils;

class ExamEventDaoImplTest {
    private List<ExamEvent> examEvents;
	private ExamEvent examEvent;
    private Exam exam;
	private int expected;
	private int actual;
	private int id;
	@Mock
	private Utils utils;
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
                                 .withDate(LocalDate.of(2017, 1, 13))
                                 .withState(ExamState.UPCOMING)
                                 .withLab(22)
                                 .build();
        id = 111;
		expected = 1;
		examEvents = new ArrayList<>();
		examEvents.add(examEvent);
    }

    @Test
	void shouldVerifyReturnValue_whileCreatingExamEvent() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		actual = examEventDaoImpl.create(examEvent);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileCreatingExamEvent() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.update(anyString(), anyMap());

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> examEventDaoImpl.create(examEvent));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(ExamEventDaoImpl.SQL_CREATE_EXAM_EVENT_ERROR));
	}

	@Test
	void shouldVerifyReturnValue_whileDeletingExamEvent() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		actual = examEventDaoImpl.delete(id);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileDeletingExamEvent() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.update(anyString(), anyMap());

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> examEventDaoImpl.delete(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(ExamEventDaoImpl.SQL_DELETE_EXAM_EVENT_ERROR));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingExamEvent() {
		when(jdbcTemplate.queryForObject(anyString(), anyMap(),
				any(ExamEventMapper.class))).thenReturn(examEvent);

		ExamEvent returnExamEvent = examEventDaoImpl.findById(id);

		assertNotNull(returnExamEvent);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingExamEvent() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.queryForObject(anyString(), anyMap(),
						any(ExamEventMapper.class));

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> examEventDaoImpl.findById(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(ExamEventDaoImpl.SQL_FIND_EXAM_EVENT_ERROR));
		assertTrue(actualMessage.contains(Integer.toString(id)));
	}

	@Test
	void shouldVerifyReturnValue_whileSearchingAllExamEvents() {
		when(jdbcTemplate.query(anyString(), any(ExamEventMapper.class)))
				.thenReturn(examEvents);

		int actual = examEventDaoImpl.findAll().size();

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingAllExamEvents() {
		doThrow(RuntimeException.class).when(jdbcTemplate)
				.query(anyString(), any(ExamEventMapper.class));

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> examEventDaoImpl.findAll());
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(ExamEventDaoImpl.SQL_FIND_ALL_EXAM_EVENTS_ERROR));
	}
}

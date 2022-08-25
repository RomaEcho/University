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
	void shouldVerifyReturnValue_whileCreatingExamEvent() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		int actual = examEventDaoImpl.create(examEvent);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileCreatingExamEvent() {
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
	void shouldVerifyReturnValue_whileDeletingExamEvent() {
		when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

		int actual = examEventDaoImpl.delete(id);

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileDeletingExamEvent() {
		when(jdbcTemplate.update(anyString(), anyMap()))
				.thenThrow(RuntimeException.class);

		Exception exception = assertThrows(
				UniversityDataAcessException.class,
				() -> examEventDaoImpl.delete(id));
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage
				.contains(ExamEventDaoImpl.SQL_DELETE_EXAM_EVENT_ERROR
						.split(SPLITTER)[COMPARED_PART]));
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
	void shouldVerifyReturnValue_whileSearchingAllExamEvents() {
		when(jdbcTemplate.query(anyString(), any(ExamEventMapper.class)))
				.thenReturn(examEvents);

		int actual = examEventDaoImpl.findAll().size();

		assertEquals(expected, actual);
	}

	@Test
	void shouldVerifyExceptionThrow_whileSearchingAllExamEvents() {
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

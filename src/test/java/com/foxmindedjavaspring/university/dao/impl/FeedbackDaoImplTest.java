package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
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

import com.foxmindedjavaspring.university.dao.impl.FeedbackDaoImpl.FeedbackMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.model.Student;

public class FeedbackDaoImplTest {
    private static final int expected = 1;
    private static final Long id = (long) 111;
    private List<Feedback> feedbacks;
    private Feedback feedback;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Mock
    private FeedbackMapper feedbackMapper;
    @InjectMocks
    private FeedbackDaoImpl feedbackDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(feedbackDao, "jdbcTemplate",
                jdbcTemplate);
        feedback = new Feedback.Builder()
                               .withRating(3)
                               .withUpdateDate(LocalDateTime.now())
                               .withUpdateDate(LocalDateTime.now())
                               .withComment(new Comment.Builder()
                                            .withText("text")
                                            .build())
                               .withStudent(new Student.Builder<>()
                                            .withStaffId((long) 333)
                                            .build())
                               .withCourse(new Course.Builder()
                                            .withTopic("topic")
                                            .build())
                               .build();
        feedbacks = List.of(feedback);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatinFeedback() {
        when(jdbcTemplate.update(eq(FeedbackDaoImpl.CREATE_FEEDBACK), 
                anyMap())).thenReturn(1);

        int actual = feedbackDao.create(feedback);

        verify(jdbcTemplate).update(eq(FeedbackDaoImpl.CREATE_FEEDBACK), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingFeedback() {
        when(jdbcTemplate.update(eq(FeedbackDaoImpl.CREATE_FEEDBACK), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FeedbackDaoImpl.SQL_CREATE_FEEDBACK_ERROR.replace("{}", "%s"), 
                feedback.getStudent().getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> feedbackDao.create(feedback));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(FeedbackDaoImpl.CREATE_FEEDBACK), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingFeedbackById() {
        when(jdbcTemplate.update(eq(FeedbackDaoImpl.DELETE_FEEDBACK_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = feedbackDao.delete(id);

        verify(jdbcTemplate).update(eq(FeedbackDaoImpl.DELETE_FEEDBACK_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingFeedbackById() {
        when(jdbcTemplate.update(eq(FeedbackDaoImpl.DELETE_FEEDBACK_BY_ID), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FeedbackDaoImpl.SQL_DELETE_FEEDBACK_BY_ID_ERROR.replace(
                    "{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> feedbackDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(FeedbackDaoImpl.DELETE_FEEDBACK_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingFeedback() {
        when(jdbcTemplate.queryForObject(eq(FeedbackDaoImpl.FIND_BY_ID), 
                anyMap(), any(FeedbackMapper.class))).thenReturn(feedback);

        Feedback returnComment = feedbackDao.findById(id);

        verify(jdbcTemplate).queryForObject(eq(FeedbackDaoImpl.FIND_BY_ID), 
                anyMap(), any(FeedbackMapper.class));
        assertNotNull(returnComment);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingFeedback() {
        when(jdbcTemplate.queryForObject(eq(FeedbackDaoImpl.FIND_BY_ID), 
                anyMap(), any(FeedbackMapper.class))).
                        thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                FeedbackDaoImpl.SQL_FIND_FEEDBACK_ERROR.replace("{}", "%s"), 
                id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> feedbackDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(FeedbackDaoImpl.FIND_BY_ID), 
                anyMap(), any(FeedbackMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllFeedbacks() {
        when(jdbcTemplate.query(eq(FeedbackDaoImpl.FIND_ALL), 
                any(FeedbackMapper.class))).thenReturn(feedbacks);

        int actual = feedbackDao.findAll().size();

        verify(jdbcTemplate).query(eq(FeedbackDaoImpl.FIND_ALL), 
                any(FeedbackMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllFeedbacks() {
        when(jdbcTemplate.query(eq(FeedbackDaoImpl.FIND_ALL), 
                any(FeedbackMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = FeedbackDaoImpl.SQL_FIND_ALL_FEEDBACKS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> feedbackDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(FeedbackDaoImpl.FIND_ALL), 
                any(FeedbackMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.dao.impl.CommentDaoImpl.CommentMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;

public class CommentDaoImplTest {
    private static final int COMPARED_PART = 2;
    private static final String SPLITTER = ":";
    private static final int expected = 1;
    private static final int id = 111;
    private List<Comment> comments;
    private Comment comment;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Mock
    private CommentMapper CommentMapper;
    @InjectMocks
    private CommentDaoImpl commentDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(commentDaoImpl, "jdbcTemplate",
                jdbcTemplate);
        comment = new Comment.Builder()
                             .withStudent(new Student.Builder<>()
                                                     .withStaffId((long) 111)
                                                     .build())      
                             .withComment("comment")
                             .withRating(5)
                             .build();
        comments = List.of(comment);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.create(comment);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.create(comment));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(CommentDaoImpl.SQL_CREATE_COMMENT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(comment.getStudent().getStaffId()
                .toString()));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingCommentById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.delete(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCommentById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(CommentDaoImpl.
                        SQL_DELETE_COMMENT_BY_ID_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingComment() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.delete(comment);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingComment() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.delete(comment));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(CommentDaoImpl.SQL_DELETE_COMMENT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(comment.getStudent().getStaffId()
                .toString()));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingComment() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(CommentMapper.class))).thenReturn(comment);

        Comment returnComment = commentDaoImpl.findById(id);

        assertNotNull(returnComment);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingComment() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(CommentMapper.class))).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(CommentDaoImpl.SQL_FIND_COMMENT_ERROR
                .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(Integer.toString(id)));
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllComments() {
        when(jdbcTemplate.query(anyString(), any(CommentMapper.class)))
                .thenReturn(comments);

        int actual = commentDaoImpl.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllComments() {
        when(jdbcTemplate.query(anyString(), any(CommentMapper.class)))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage
                .contains(CommentDaoImpl.SQL_FIND_ALL_COMMENTS_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
    }

    @Test
    void shouldVerifyReturnValueWhileUpdatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.updateComment(comment);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileUpdatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.updateComment(comment));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(CommentDaoImpl.SQL_UPDATE_COMMENT_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(comment.getStudent().getStaffId()
                .toString()));
    }

    @Test
    void shouldVerifyReturnValueWhileUpdatingRating() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.updateRating(comment);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileUpdatingRating() {
        Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("staff_id", comment.getStudent().getStaffId());
            namedParameters.put("rating", comment.getRating());
            namedParameters.put("rating_update", 
                    new Timestamp(System.currentTimeMillis()));
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.updateRating(comment));
        String actualMessage = exception.getMessage();

        assertTrue(
                actualMessage.contains(CommentDaoImpl.SQL_UPDATE_RATING_ERROR
                        .split(SPLITTER)[COMPARED_PART]));
        assertTrue(actualMessage.contains(comment.getStudent().getStaffId()
                .toString()));
    }

}

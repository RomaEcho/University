package com.foxmindedjavaspring.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.foxmindedjavaspring.university.dao.impl.CommentDaoImpl.CommentMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Comment;

public class CommentDaoImplTest {
    private static final int expected = 1;
    private static final Long id = (long) 111;
    private static final Long feedbackId = (long) 1121;
    private static final String text = "text";
    private List<Comment> comments;
    private Comment comment;
    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Mock
    private CommentMapper CommentMapper;
    @InjectMocks
    private CommentDaoImpl commentDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(commentDao, "jdbcTemplate",
                jdbcTemplate);
        comment = new Comment.Builder()
                             .withText("text")
                             .build();
        comments = List.of(comment);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingComment() {
        when(jdbcTemplate.update(eq(CommentDaoImpl.CREATE_COMMENT), anyMap()))
                .thenReturn(1);

        int actual = commentDao.create(text, feedbackId);

        verify(jdbcTemplate).update(eq(CommentDaoImpl.CREATE_COMMENT), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingComment() {
        when(jdbcTemplate.update(eq(CommentDaoImpl.CREATE_COMMENT), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_CREATE_COMMENT_ERROR.replace("{}", "%s"), 
                feedbackId);

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDao.create(text, feedbackId));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(CommentDaoImpl.CREATE_COMMENT), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingCommentById() {
        when(jdbcTemplate.update(eq(CommentDaoImpl.DELETE_COMMENT_BY_ID), 
                anyMap())).thenReturn(1);

        int actual = commentDao.delete(id);

        verify(jdbcTemplate).update(eq(CommentDaoImpl.DELETE_COMMENT_BY_ID), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCommentById() {
        when(jdbcTemplate.update(eq(CommentDaoImpl.DELETE_COMMENT_BY_ID), 
                anyMap())).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_DELETE_COMMENT_BY_ID_ERROR.replace(
                    "{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDao.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(CommentDaoImpl.DELETE_COMMENT_BY_ID), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingComment() {
        when(jdbcTemplate.queryForObject(eq(CommentDaoImpl.FIND_BY_ID),
                anyMap(), any(CommentMapper.class))).thenReturn(comment);

        Comment returnComment = commentDao.findById(id);

        verify(jdbcTemplate).queryForObject(eq(CommentDaoImpl.FIND_BY_ID), 
                anyMap(),  any(CommentMapper.class));
        assertNotNull(returnComment);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingComment() {
        when(jdbcTemplate.queryForObject(eq(CommentDaoImpl.FIND_BY_ID), 
                anyMap(),
                any(CommentMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_FIND_COMMENT_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDao.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(eq(CommentDaoImpl.FIND_BY_ID), 
                anyMap(), any(CommentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllComments() {
        when(jdbcTemplate.query(eq(CommentDaoImpl.FIND_ALL), 
                any(CommentMapper.class))).thenReturn(comments);

        int actual = commentDao.findAll().size();

        verify(jdbcTemplate).query(eq(CommentDaoImpl.FIND_ALL), 
                any(CommentMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllComments() {
        when(jdbcTemplate.query(eq(CommentDaoImpl.FIND_ALL), 
                any(CommentMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = CommentDaoImpl.SQL_FIND_ALL_COMMENTS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDao.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(eq(CommentDaoImpl.FIND_ALL), 
                any(CommentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileUpdatingComment() {
        when(jdbcTemplate.update(eq(CommentDaoImpl.UPDATE_COMMENT), anyMap())).
                thenReturn(1);

        int actual = commentDao.update(text, feedbackId);

        verify(jdbcTemplate).update(eq(CommentDaoImpl.UPDATE_COMMENT), 
                anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileUpdatingComment() {
        when(jdbcTemplate.update(eq(CommentDaoImpl.UPDATE_COMMENT), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_UPDATE_COMMENT_ERROR.replace("{}", "%s"), 
                feedbackId);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDao.update(text, feedbackId));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(eq(CommentDaoImpl.UPDATE_COMMENT), 
                anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

}

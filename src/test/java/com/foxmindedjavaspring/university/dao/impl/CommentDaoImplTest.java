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

import com.foxmindedjavaspring.university.dao.impl.CommentDaoImpl.CommentMapper;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.model.Student;

public class CommentDaoImplTest {
    private static final int expected = 1;
    private static final int id = 111;
    private List<Comment> comments;
    private Comment comment;
    private Feedback feedback;
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
                             .withText("text")
                             .build();
        feedback = new Feedback.Builder()
                               .withComment(comment)
                               .withUpdateDate(LocalDateTime.now())
                               .withUpdateDate(LocalDateTime.now())
                               .withStudent(new Student.Builder<>()
                                            .withStaffId((long) 333)
                                            .build())
                               .withRating(3)
                               .build();
        comments = List.of(comment);
    }

    @Test
    void shouldVerifyReturnValueWhileCreatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.create(feedback);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileCreatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_CREATE_COMMENT_ERROR.replace("{}", "%s"), 
                feedback.getStudent().getStaffId());

        Exception exception = assertThrows(
                UniversityDataAcessException.class,
                () -> commentDaoImpl.create(feedback));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingCommentById() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.delete(id);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingCommentById() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_DELETE_COMMENT_BY_ID_ERROR.replace(
                    "{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDaoImpl.delete(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileDeletingComment() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.delete(feedback);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileDeletingComment() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_DELETE_COMMENT_ERROR.replace("{}", "%s"), 
                feedback.getStudent().getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDaoImpl.delete(feedback));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingComment() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(CommentMapper.class))).thenReturn(comment);

        Comment returnComment = commentDaoImpl.findById(id);

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(CommentMapper.class));
        assertNotNull(returnComment);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingComment() {
        when(jdbcTemplate.queryForObject(anyString(), anyMap(),
                any(CommentMapper.class))).thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_FIND_COMMENT_ERROR.replace("{}", "%s"), id);

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDaoImpl.findById(id));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).queryForObject(anyString(), anyMap(),
                any(CommentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileSearchingAllComments() {
        when(jdbcTemplate.query(anyString(), any(CommentMapper.class)))
                .thenReturn(comments);

        int actual = commentDaoImpl.findAll().size();

        verify(jdbcTemplate).query(anyString(), any(CommentMapper.class));
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileSearchingAllComments() {
        when(jdbcTemplate.query(anyString(), any(CommentMapper.class)))
                .thenThrow(RuntimeException.class);
        String expectedMessage = CommentDaoImpl.SQL_FIND_ALL_COMMENTS_ERROR;

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDaoImpl.findAll());
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).query(anyString(), any(CommentMapper.class));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldVerifyReturnValueWhileUpdatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        int actual = commentDaoImpl.update(feedback);

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyExceptionThrowWhileUpdatingComment() {
        when(jdbcTemplate.update(anyString(), anyMap()))
                .thenThrow(RuntimeException.class);
        String expectedMessage = String.format(
                CommentDaoImpl.SQL_UPDATE_COMMENT_ERROR.replace("{}", "%s"), 
                feedback.getStudent().getStaffId());

        Exception exception = assertThrows(UniversityDataAcessException.class,
                () -> commentDaoImpl.update(feedback));
        String actualMessage = exception.getMessage();

        verify(jdbcTemplate).update(anyString(), anyMap());
        assertEquals(expectedMessage, actualMessage);
    }

}

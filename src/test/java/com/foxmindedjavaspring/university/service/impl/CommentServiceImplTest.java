package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Comment;

import java.util.Optional;

public class CommentServiceImplTest {
    private static final long id = 11;
    private Comment comment;
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        comment = new Comment();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewComment() {
        commentService.addComment(comment);

        verify(commentRepository).save(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingComment() {
        commentService.editComment(comment);

        verify(commentRepository).save(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingComment() {
        commentService.removeComment(comment);

        verify(commentRepository).delete(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingComment() {
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));

        commentService.getComment(id);

        verify(commentRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllComments() {
        commentService.getAllComments();

        verify(commentRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingComment() {
        when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                CommentServiceImpl.GET_COMMENT_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> commentService.getComment(id));
        String actualMessage = exception.getMessage();

        verify(commentRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}

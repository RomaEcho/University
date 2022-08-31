package com.foxmindedjavaspring.university.service;

public interface CommentService<T> extends GenericService<T> {
    void editComment(T comment);

    void editRating(T comment);
}

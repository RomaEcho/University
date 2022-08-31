package com.foxmindedjavaspring.university.dao;

public interface CommentDao<T> extends GenericDao<T> {
    int updateComment(T comment);

    int updateRating(T comment);
}

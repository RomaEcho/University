package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.Comment;

@Repository
public class CommentDao extends AbstractGenericDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }

}

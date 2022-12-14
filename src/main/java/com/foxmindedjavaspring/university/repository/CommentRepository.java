package com.foxmindedjavaspring.university.repository;

import com.foxmindedjavaspring.university.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

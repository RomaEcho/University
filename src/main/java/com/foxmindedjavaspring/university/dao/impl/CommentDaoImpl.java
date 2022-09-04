package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Feedback;

@Repository
public class CommentDaoImpl implements CommentDao {
    static final String CREATE_COMMENT = 
          "INSERT INTO comments ( "
          + "feedback_id, "
          + "text, "
          + "creation_date, "
          + "update_date ) "
        + "VALUES ( "
        +   "( SELECT id "
            + "FROM feedbacks "
            + "WHERE student_id = "
                + "( SELECT id "
                    + "FROM students "
                    + "WHERE staff_id = :staff_id ) ), "
            + ":text, "
            + ":creation_date, "
            + ":update_date, ";
    static final String DELETE_COMMENT_BY_ID = 
          "DELETE FROM comments "
        + "WHERE id = :id";
    static final String DELETE_COMMENT = 
          "DELETE FROM comments "
        + "WHERE feedback_id IN ( "
            + "SELECT id "
            + "FROM feedbacks "
            + "WHERE student_id IN ( "
                + "SELECT id "
                + "FROM students "
                + "WHERE staff_id = :staff_id )";
    static final String FIND_BY_ID = 
        "SELECT "
            + "text, "
            + "creation_date, "
            + "update_date "
        + "FROM comments "
        + "WHERE id = :id";
    static final String FIND_ALL = 
        "SELECT "
            + "text, "
            + "creation_date, "
            + "update_date "
       + "FROM comments";
    static final String UPDATE_COMMENT = 
          "UPDATE comments "
        + "SET "
            + "text = :text, "
            + "update_date = :update_date "
        + "WHERE feedback_id IN ( "
            + "SELECT id "
            + "FROM feedbacks "
            + "WHERE student_id IN ( "
                + "SELECT id "
                + "FROM students "
                + "WHERE staff_id = :staff_id )";
    static final String SQL_CREATE_COMMENT_ERROR = " :: Error while creating the comment for the student with staff_id: {}";
    static final String SQL_DELETE_COMMENT_BY_ID_ERROR = " :: Error while deleting the comment with id: {}";
    static final String SQL_DELETE_COMMENT_ERROR = " :: Error while deleting the comment for the student with staff_id: {}";
    static final String SQL_FIND_COMMENT_ERROR = " :: Error while searching the comment with id: {}";
    static final String SQL_FIND_ALL_COMMENTS_ERROR = " :: Error while searching all comments.";
    static final String SQL_UPDATE_COMMENT_ERROR = " :: Error while updating the comment for the student with staff_id: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CommentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Feedback feedback) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("staff_id", feedback.getStudent().getStaffId());
            namedParameters.put("text", feedback.getComment().getText());
            namedParameters.put("rating", feedback.getRating());
            if (feedback.getComment() != null) {
                namedParameters.put("creation_date", 
                    new Timestamp(System.currentTimeMillis()));
                namedParameters.put("update_date", 
                    new Timestamp(System.currentTimeMillis()));
            }
            return jdbcTemplate.update(CREATE_COMMENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_COMMENT_ERROR,
                    feedback.getStudent().getStaffId());
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_COMMENT_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_COMMENT_BY_ID_ERROR, id);
        }
    }

    @Override
    public int delete(Feedback feedback) {
        try {
            return jdbcTemplate.update(DELETE_COMMENT, 
                    Collections.singletonMap("staff_id", 
                    feedback.getStudent().getStaffId()));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_COMMENT_ERROR,
                    feedback.getStudent().getStaffId());
        }
    }

    @Override
    public Comment findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), 
                    new CommentMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_FIND_COMMENT_ERROR,
                    id);
        }
    }

    @Override
    public List<Comment> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new CommentMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_FIND_ALL_COMMENTS_ERROR);
        }
    }

    @Override
    public int update(Feedback feedback) {
        try {
            Map<String, Object> namedParameters = Map.of(
                    "text", feedback.getComment().getText(),
                    "update_date", new Timestamp(System.currentTimeMillis()),
                    "staff_id", feedback.getStudent().getStaffId());
            return jdbcTemplate.update(UPDATE_COMMENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_UPDATE_COMMENT_ERROR,
                    feedback.getStudent().getStaffId());
        }
    }

    static class CommentMapper implements RowMapper<Comment> {
        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Comment.Builder()
                              .withText(rs.getString("text"))
                              .withUpdateDate(rs.getTimestamp("update_date")
                                    .toLocalDateTime())
                              .withCreationDate(rs.getTimestamp("creation_date")
                                    .toLocalDateTime()) 
                              .build();
        }
    }
}

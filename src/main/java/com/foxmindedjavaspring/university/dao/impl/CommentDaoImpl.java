package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {
    static final String CREATE_COMMENT = 
          "INSERT INTO comments ( "
          + "feedback_id, "
          + "text, "
          + "creation_date, "
          + "update_date ) "
        + "VALUES ( "
            + ":id, "
            + ":text, "
            + ":creation_date, "
            + ":update_date, ";
    static final String DELETE_COMMENT_BY_ID = 
          "DELETE FROM "
            + "comments "
        + "WHERE "
            + "id = :id";
    static final String FIND_BY_ID = 
        "SELECT "
            + "text, "
            + "creation_date, "
            + "update_date "
        + "FROM "
            + "comments "
        + "WHERE "
            + "id = :id";
    static final String FIND_ALL = 
        "SELECT "
            + "text, "
            + "creation_date, "
            + "update_date "
       + "FROM "
            + "comments";
    static final String UPDATE_COMMENT = 
          "UPDATE "
            + "comments "
        + "SET "
            + "text = :text, "
            + "update_date = :update_date "
        + "WHERE "
            + "feedback_id = :id";
    static final String SQL_CREATE_COMMENT_ERROR = " :: Error while creating the comment for the feedback with id: {}";
    static final String SQL_DELETE_COMMENT_BY_ID_ERROR = " :: Error while deleting the comment with id: {}";
    static final String SQL_FIND_COMMENT_ERROR = " :: Error while searching the comment with id: {}";
    static final String SQL_FIND_ALL_COMMENTS_ERROR = " :: Error while searching all comments.";
    static final String SQL_UPDATE_COMMENT_ERROR = " :: Error while updating the comment for the feedback with id: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(
                CommentDaoImpl.class);

    public CommentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(String text, Long feedbackId) {
        try {
            LOG.debug("Trying to create the comment for feedback with id: {} using the following SQL: {}", 
                    feedbackId, CREATE_COMMENT);
            Map<String, Object> namedParameters = Map.of(
                    "id", feedbackId,
                    "text", text,
                    "creation_date", new Timestamp(System.currentTimeMillis()),
                    "update_date", new Timestamp(System.currentTimeMillis()));
            return jdbcTemplate.update(CREATE_COMMENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_CREATE_COMMENT_ERROR,
                    feedbackId);
        }
    }

    @Override
    public int delete(Long id) {
        try {
            LOG.debug("Trying to delete the comment with id: {} using the following SQL: {}", 
                    id, DELETE_COMMENT_BY_ID);
            return jdbcTemplate.update(DELETE_COMMENT_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_COMMENT_BY_ID_ERROR, id);
        }
    }

    @Override
    public Comment findById(Long id) {
        try {
            LOG.debug("Trying to find the comment with id: {} using the following SQL: {}", 
                    id, FIND_BY_ID);
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
            LOG.debug("Trying to find all the comments using the following SQL: {}", 
                    FIND_ALL);
            return jdbcTemplate.query(FIND_ALL, new CommentMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_FIND_ALL_COMMENTS_ERROR);
        }
    }

    @Override
    public int update(String text, Long feedbackId) {
        try {
            LOG.debug("Trying to update the comment with text: {} for feedback with id: {} using the following SQL: {}", 
                    text, feedbackId, UPDATE_COMMENT);
            Map<String, Object> namedParameters = Map.of(
                    "text", text,
                    "update_date", new Timestamp(System.currentTimeMillis()));
            return jdbcTemplate.update(UPDATE_COMMENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_UPDATE_COMMENT_ERROR,
                    feedbackId);
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

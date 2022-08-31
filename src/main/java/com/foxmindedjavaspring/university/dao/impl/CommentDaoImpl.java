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
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.Subject;

@Repository
public class CommentDaoImpl implements CommentDao<Comment> {
    static final String CREATE_COMMENT = 
          "INSERT INTO comments ( "
          + "student_id, "
          + "rating, "
          + "rating_update "
          + "comment, "
          + "comment_update ) "
        + "VALUES ( "
        +   "( "
                + "SELECT id "
                + "FROM students "
                + "WHERE staff_id = :staff_id ), "
            + ":rating, "
            + ":rating_update, "
            + ":comment, "
            + ":comment_update ) ";
    static final String DELETE_COMMENT_BY_ID = 
          "DELETE FROM comments "
        + "WHERE id = :id";
    static final String DELETE_COMMENT = 
          "DELETE FROM comments "
        + "WHERE student_id IN ( "
            + "SELECT id "
            + "FROM students "
            + "WHERE staff_id = :staff_id )";
    static final String FIND_BY_ID = 
        "SELECT "
            + "comments.rating AS rating, "
            + "comments.rating_date AS rating_date, "
            + "comments.comment AS comment, "
            + "comments.comment_date AS comment_date, "
            + "students.staff_id AS student_staff_id, "
            + "courses.topic AS topic, "
            + "courses.start_date AS start_date, "
            + "courses.end_date AS end_date, "
            + "courses.number_of_hours AS number_of_hours, "
            + "courses.rate AS rate, "
            + "lecturers.staff_id AS lecturer_staff_id, "
            + "subjects.number AS number, "
            + "subjects.name AS name "
        + "FROM comments "
        + "JOIN students "
            + "ON comments.student_id = students.id "
        + "JOIN courses "
            + "ON comments.course_id = courses.id "
        + "JOIN lecturers "
            + "ON courses.lecturer_id = lecturers.id "
        + "JOIN subjects "
            + "ON courses.subject_id = subjects.id "
        + "WHERE comments.id = :id";
    static final String FIND_ALL = 
        "SELECT "
            + "comments.rating AS rating, "
            + "comments.rating_date AS rating_date, "
            + "comments.comment AS comment, "
            + "comments.comment_date AS comment_date, "
            + "students.staff_id AS staff_id, "
            + "courses.topic AS topic, "
            + "courses.start_date AS start_date, "
            + "courses.end_date AS end_date, "
            + "courses.number_of_hours AS number_of_hours, "
            + "courses.rate AS rate, "
            + "lecturers.staff_id AS lecturer_staff_id, "
            + "subjects.number AS number, "
            + "subjects.name AS name "
        + "FROM comments "
        + "JOIN students "
            + "ON comments.student_id = students.id "
        + "JOIN courses "
            + "ON comments.course_id = courses.id "
        + "JOIN lecturers "
            + "ON courses.lecturer_id = lecturers.id "
        + "JOIN subjects "
            + "ON courses.subject_id = subjects.id";
    static final String UPDATE_COMMENT = 
          "UPDATE comments "
        + "SET "
            + "comment = :comment, "
            + "comment_date = :comment_date, "
        + "WHERE student_id IN ( "
            + "SELECT id "
            + "FROM students "
            + "WHERE staff_id = :staff_id )";
    static final String UPDATE_RATING = 
          "UPDATE comments "
        + "SET "
            + "rating = :rating, "
            + "rating_date = :rating_date "
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
    static final String SQL_UPDATE_RATING_ERROR = " :: Error while updating the rating for the student with staff_id: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CommentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Comment comment) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("staff_id", comment.getStudent().getStaffId());
            namedParameters.put("rating", comment.getRating());
            namedParameters.put("comment", comment.getComment());
            if (comment.getComment() != null) {
                namedParameters.put("comment_update", 
                    new Timestamp(System.currentTimeMillis()));
            }
            if (comment.getRating() != null) {
                namedParameters.put("rating_update", 
                    new Timestamp(System.currentTimeMillis()));
            }
            return jdbcTemplate.update(CREATE_COMMENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_COMMENT_ERROR,
                    comment.getStudent().getStaffId());
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
    public int delete(Comment comment) {
        try {
            return jdbcTemplate.update(DELETE_COMMENT, 
                    Collections.singletonMap("staff_id", 
                    comment.getStudent().getStaffId()));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_COMMENT_ERROR,
                    comment.getStudent().getStaffId());
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
    public int updateComment(Comment comment) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("staff_id", comment.getStudent().getStaffId());
            namedParameters.put("comment", comment.getComment());
            namedParameters.put("comment_update", 
                    new Timestamp(System.currentTimeMillis()));
            return jdbcTemplate.update(UPDATE_COMMENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_UPDATE_COMMENT_ERROR,
                    comment.getStudent().getStaffId());
        }
    }

    @Override
    public int updateRating(Comment comment) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("staff_id", comment.getStudent().getStaffId());
            namedParameters.put("rating", comment.getRating());
            namedParameters.put("rating_update", 
                    new Timestamp(System.currentTimeMillis()));
            return jdbcTemplate.update(UPDATE_RATING, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_UPDATE_RATING_ERROR,
                    comment.getStudent().getStaffId());
        }
    }

    static class CommentMapper implements RowMapper<Comment> {
        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Comment.Builder()
                    .withComment(rs.getString("comment"))
                    .withCommentUpdate(rs.getTimestamp(
                        "comment_update").toLocalDateTime())
                    .withRating(rs.getInt("rating"))
                    .withRatingUpdate(rs.getTimestamp(
                        "rating_update").toLocalDateTime())
                    .withStudent(new Student.Builder<>()
                            .withStaffId(rs.getLong("student_staff_id"))
                            .build())
                    .withCourse(new Course.Builder()
                            .withEndDate(rs.getDate("end_date").toLocalDate())
                            .withNumberOfHours(rs.getInt("number_of_hours"))
                            .withRate(rs.getInt("rate"))
                            .withStartDate(rs.getDate("start_date").toLocalDate())
                            .withTopic(rs.getString("topic")) 
                            .withLecturer(new Lecturer.Builder<>()
                                .withStaffId(rs.getLong("lecturer_staff_id"))
                                .build())
                            .withSubject(new Subject(rs.getInt("number"),
                                rs.getString("name")))
                            .build())
                    .build();
        }
    }
}

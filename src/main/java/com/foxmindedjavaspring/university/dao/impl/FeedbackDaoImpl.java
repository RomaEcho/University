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

import com.foxmindedjavaspring.university.dao.FeedbackDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.Subject;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {
    static final String CREATE_FEEDBACK = 
          "INSERT INTO feedbacks ( "
          + "student_id, "
          + "course_id, "
          + "rating, "
          + "creation_date, "
          + "update_date ) "
        + "VALUES ( "
        +   "( SELECT id "
            + "FROM students "
            + "WHERE staff_id = :staff_id ), "
        +   "( SELECT id "
            + "FROM courses "
            + "WHERE topic = :topic ), "
        + ":rating, "
        + ":creation_date, "
        + ":update_date, ";
    static final String DELETE_FEEDBACK_BY_ID = 
          "DELETE FROM feedbacks "
        + "WHERE id = :id";
    static final String FIND_BY_ID = 
        "SELECT "
            + "f.rating AS rating, "
            + "f.rating_date AS rating_date, "
            + "f.comment AS comment, "
            + "f.comment_date AS comment_date, "
            + "st.staff_id AS student_staff_id, "
            + "cr.topic AS topic, "
            + "cr.start_date AS start_date, "
            + "cr.end_date AS end_date, "
            + "cr.number_of_hours AS number_of_hours, "
            + "cr.rate AS rate, "
            + "le.staff_id AS lecturer_staff_id, "
            + "le.number AS number, "
            + "su.name AS name "
        + "FROM feedbacks f "
        + "JOIN students st"
            + "ON f.student_id = st.id "
        + "JOIN courses cr"
            + "ON f.course_id = cr.id "
        + "JOIN lecturers le"
            + "ON cr.lecturer_id = le.id "
        + "JOIN subjects su"
            + "ON cr.subject_id = su.id "
        + "WHERE f.id = :id";
    static final String FIND_ALL = 
        "SELECT "
            + "f.rating AS rating, "
            + "f.rating_date AS rating_date, "
            + "f.comment AS comment, "
            + "f.comment_date AS comment_date, "
            + "st.staff_id AS student_staff_id, "
            + "cr.topic AS topic, "
            + "cr.start_date AS start_date, "
            + "cr.end_date AS end_date, "
            + "cr.number_of_hours AS number_of_hours, "
            + "cr.rate AS rate, "
            + "le.staff_id AS lecturer_staff_id, "
            + "le.number AS number, "
            + "su.name AS name "
        + "FROM feedbacks f "
        + "JOIN students st"
            + "ON f.student_id = st.id "
        + "JOIN courses cr"
            + "ON f.course_id = cr.id "
        + "JOIN lecturers le"
            + "ON cr.lecturer_id = le.id "
        + "JOIN subjects su"
            + "ON cr.subject_id = su.id";
    static final String UPDATE_FEEDBACK = 
          "UPDATE "
            + "feedbacks "
        + "SET "
            + "rating = :rating, "
            + "update_date = :update_date "
        + "WHERE "
            + "feedback_id = :id";
    static final String SQL_CREATE_FEEDBACK_ERROR = " :: Error while creating the feedback for the student with staff_id: {}";
    static final String SQL_DELETE_FEEDBACK_BY_ID_ERROR = " :: Error while deleting the feedback with id: {}";
    static final String SQL_FIND_FEEDBACK_ERROR = " :: Error while searching the feedback with id: {}";
    static final String SQL_FIND_ALL_FEEDBACKS_ERROR = " :: Error while searching all feedbacks.";
    static final String SQL_UPDATE_FEEDBACK_ERROR = " :: Error while updating the feedback for the student with staff_id: {}";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FeedbackDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Feedback feedback) {
        try {
            Map<String, Object> namedParameters = Map.of(
                    "staff_id", feedback.getStudent().getStaffId(),
                    "topic", feedback.getCourse().getTopic(),
                    "rating", feedback.getRating(),
                    "creation_date", new Timestamp(System.currentTimeMillis()),
                    "update_date", new Timestamp(System.currentTimeMillis()));
            return jdbcTemplate.update(CREATE_FEEDBACK, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_FEEDBACK_ERROR,
                    feedback.getStudent().getStaffId());
        }
    }

    @Override
    public int delete(Long id) {
        try {
            return jdbcTemplate.update(DELETE_FEEDBACK_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_FEEDBACK_BY_ID_ERROR, id);
        }
    }

    @Override
    public int update(Integer rating, Long feedbackId) {
        try {
            Map<String, Object> namedParameters = Map.of(
                    "id", feedbackId,
                    "rating", rating,
                    "update_date", new Timestamp(System.currentTimeMillis()));
            return jdbcTemplate.update(UPDATE_FEEDBACK, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_UPDATE_FEEDBACK_ERROR,
                    feedbackId);
        }
    }

    @Override
    public Feedback findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), 
                    new FeedbackMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_FIND_FEEDBACK_ERROR,
                    id);
        }
    }

    @Override
    public List<Feedback> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new FeedbackMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_FIND_ALL_FEEDBACKS_ERROR);
        }
    }

    static class FeedbackMapper implements RowMapper<Feedback> {
        @Override
        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Feedback.Builder()
                    .withRating(rs.getInt("rating"))
                    .withUpdateDate(rs.getTimestamp("update_date")
                            .toLocalDateTime())
                    .withCreationDate(rs.getTimestamp("creation_date")
                            .toLocalDateTime()) 
                    .withComment(new Comment.Builder()
                            .withText(rs.getString("text"))
                            .build())
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

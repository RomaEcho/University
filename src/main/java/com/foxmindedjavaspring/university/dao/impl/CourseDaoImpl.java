package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.model.Subject;

@Repository
public class CourseDaoImpl implements GenericDao<Course> {
    static final String CREATE_COURSE = 
          "INSERT INTO courses(topic, number_of_hours) "
        + "VALUES(:topic, :number_of_hours)";
    static final String DELETE_COURSE_BY_ID = 
          "DELETE FROM courses WHERE id = :id";
    static final String DELETE_COURSE = 
          "DELETE FROM courses co"
        + "WHERE "
            + "co.topic = :topic AND "
            + "co.number_of_hours = :number_of_hours AND "
            + "co.lecturer_id IN ( "
                + "SELECT "
                    + "id "
                + "FROM lecturers le"
                + "WHERE le.staff_id = :staff_id) AND "
            + "co.subject_id IN ( "
                + "SELECT "
                    + "id "
                + "FROM subjects su"
                + "WHRERE su.number = :number)";
    static final String FIND_BY_ID = 
        "SELECT "
            + "co.topic AS topic, "
            + "co.description AS course_description, "
            + "co.start_date AS start_date, "
            + "co.end_date AS end_date, "
            + "co.number_of_hours AS number_of_hours, "
            + "co.rate AS rate, "
            + "le.staff_id AS staff_id, "
            + "le.level AS level, "
            + "su.number AS number, "
            + "su.name AS name, "
            + "su.description  AS subject_description "
        + "FROM "
            + "courses co"
        + "JOIN lecturers le"
            + "ON co.lecturer_id = le.id "
        + "JOIN subjects su"
            + "ON co.subject_id = su.id "
        + "WHERE courses.id = :id";
    static final String FIND_ALL = 
        "SELECT "
            + "co.topic AS topic, "
            + "co.description AS course_description, "
            + "co.start_date AS start_date, "
            + "co.end_date AS end_date, "
            + "co.number_of_hours AS number_of_hours, "
            + "co.rate AS rate, "
            + "le.staff_id AS staff_id, "
            + "le.level AS level, "
            + "su.number AS number, "
            + "su.name AS name, "
            + "su.description  AS subject_description "
        + "FROM "
            + "courses co"
        + "JOIN lecturers le"
            + "ON co.lecturer_id = le.id "
        + "JOIN subjects su"
            + "ON co.subject_id = su.id";
    static final String SQL_CREATE_COURSE_ERROR = " :: Error while creating the course with topic: {}";
    static final String SQL_DELETE_COURSE_BY_ID_ERROR = " :: Error while deleting the course with id: {}";
    static final String SQL_DELETE_COURSE_ERROR = " :: Error while deleting the course with topic: {}, lecturer staff_id: {}, subject: {}";
    static final String SQL_FIND_COURSE_ERROR = " :: Error while searching the course with id: {}";
    static final String SQL_FIND_ALL_COURSES_ERROR = " :: Error while searching all courses.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CourseDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Course course) {
        try {
            Map<String, Object> namedParameters = Map.of(
                    "topic", course.getTopic(),
                    "number_of_hours", course.getNumberOfHours());
            return jdbcTemplate.update(CREATE_COURSE, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_CREATE_COURSE_ERROR,
                    course.getTopic());
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_COURSE_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_COURSE_BY_ID_ERROR,
                    id);
        }
    }

    @Override
    public int delete(Course course) {
        try {
            Map<String, Object> namedParameters = Map.of(
                    "topic", course.getTopic(),
                    "staff_id", course.getLecturer().getStaffId(),
                    "number", course.getSubject().getNumber(),
                    "number_of_hours", course.getNumberOfHours()); 
            return jdbcTemplate.update(DELETE_COURSE, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_COURSE_ERROR,
                    course.getTopic(),
                    course.getLecturer().getStaffId(),
                    course.getSubject().getName());
        }
    }

    @Override
    public Course findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id), new CourseMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, SQL_FIND_COURSE_ERROR,
                    id);
        }
    }

    @Override
    public List<Course> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new CourseMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_FIND_ALL_COURSES_ERROR);
        }
    }

    static class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course.Builder()
                    .withDescription(rs.getString("description"))
                    .withEndDate(rs.getDate("end_date").toLocalDate())
                    .withLecturer(new Lecturer.Builder<>()
                            .withLevel(rs.getString("level"))
                            .withStaffId(rs.getLong("staff_id"))
                            .build())
                    .withNumberOfHours(rs.getInt("number_of_hours"))
                    .withRate(rs.getInt("rate"))
                    .withStartDate(rs.getDate("start_date").toLocalDate())
                    .withSubject(new Subject(rs.getInt("number"),
                            rs.getString("name")))
                    .withTopic(rs.getString("topic")).build();
        }
    }
}

package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class CourseDaoImpl implements GenericDao<Course> {
    public static final String CREATE_COURSE = "INSERT INTO courses(topic, number_of_hours) VALUES(:topic, :number_of_hours)";
    public static final String DELETE_COURSE = "DELETE FROM courses WHERE id = :id";
    public static final String FIND_BY_ID = 
        "SELECT "
            + "courses.topic AS topic, "
            + "courses.description AS course_description, "  
            + "courses.start_date AS start_date, "
            + "courses.end_date AS end_date, "
            + "courses.number_of_hours AS number_of_hours, "
            + "courses.rate AS rate, "
            + "lecturers.staff_id AS staff_id, "
            + "lecturers.level AS level, "
            + "subjects.number AS number, "
            + "subjects.name AS name, "
            + "subjects.description  AS subject_description "
        + "FROM "
            + "courses "
        + "JOIN lecturers "
            + "ON courses.lecturer_id = lecturers.id "
        + "JOIN subjects "
            + "ON courses.subject_id = subjects.id "
        + "WHERE courses.id = :id";
    public static final String FIND_ALL = 
        "SELECT "
            + "courses.topic AS topic, "
            + "courses.description AS course_description, "  
            + "courses.start_date AS start_date, "
            + "courses.end_date AS end_date, "
            + "courses.number_of_hours AS number_of_hours, "
            + "courses.rate AS rate, "
            + "lecturers.staff_id AS staff_id, "
            + "lecturers.level AS level, "
            + "subjects.number AS number, "
            + "subjects.name AS name, "
            + "subjects.description  AS subject_description "
        + "FROM "
            + "courses "
        + "JOIN lecturers "
            + "ON courses.lecturer_id = lecturers.id "
        + "JOIN subjects "
            + "ON courses.subject_id = subjects.id";
    public static final String SQL_CREATE_COURSE_ERROR = " :: Error while creating the course with topic: ";
    public static final String SQL_DELETE_COURSE_ERROR = " :: Error while deleting the course with id: ";
    public static final String SQL_FIND_COURSE_ERROR = " :: Error while searching the course with id: ";
    public static final String SQL_FIND_ALL_COURSES_ERROR = " :: Error while searching all courses.";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Utils utils;

    public CourseDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, Utils utils) {
        this.jdbcTemplate = jdbcTemplate;
        this.utils = utils;
    }

    @Override
    public int create(Course course) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("topic", course.getTopic());
            namedParameters.put("number_of_hours", course.getNumberOfHours());
            return jdbcTemplate.update(CREATE_COURSE, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_CREATE_COURSE_ERROR + course.getTopic(), e);
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_COURSE,
                utils.getMapSinglePair("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_DELETE_COURSE_ERROR + id, e);
        }
    }

    @Override
    public Course findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    utils.getMapSinglePair("id", id), new CourseMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_FIND_COURSE_ERROR + id, e);
        }
    }

    @Override
    public List<Course> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new CourseMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(
                    SQL_FIND_ALL_COURSES_ERROR, e);
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

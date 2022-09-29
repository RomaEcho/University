package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static final String FIND_BY_ID = 
        "SELECT "
            + "c.topic AS topic, "
            + "c.description AS course_description, "
            + "c.start_date AS start_date, "
            + "c.end_date AS end_date, "
            + "c.number_of_hours AS number_of_hours, "
            + "c.rate AS rate, "
            + "l.staff_id AS staff_id, "
            + "l.level AS level, "
            + "s.number AS number, "
            + "s.name AS name, "
            + "s.description  AS subject_description "
        + "FROM "
            + "courses c"
        + "JOIN lecturers l"
            + "ON c.lecturer_id = l.id "
        + "JOIN subjects s"
            + "ON c.subject_id = s.id "
        + "WHERE courses.id = :id";
    static final String FIND_ALL = 
        "SELECT "
            + "c.topic AS topic, "
            + "c.description AS course_description, "
            + "c.start_date AS start_date, "
            + "c.end_date AS end_date, "
            + "c.number_of_hours AS number_of_hours, "
            + "c.rate AS rate, "
            + "l.staff_id AS staff_id, "
            + "l.level AS level, "
            + "s.number AS number, "
            + "s.name AS name, "
            + "s.description  AS subject_description "
        + "FROM "
            + "courses c"
        + "JOIN lecturers l"
            + "ON c.lecturer_id = l.id "
        + "JOIN subjects s"
            + "ON c.subject_id = s.id";
    static final String SQL_CREATE_COURSE_ERROR = " :: Error while creating the course with topic: {}";
    static final String SQL_DELETE_COURSE_BY_ID_ERROR = " :: Error while deleting the course with id: {}";
    static final String SQL_FIND_COURSE_ERROR = " :: Error while searching the course with id: {}";
    static final String SQL_FIND_ALL_COURSES_ERROR = " :: Error while searching all courses.";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(
                CourseDaoImpl.class);

    public CourseDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Course course) {
        try {
            LOG.debug("Trying to create the course with topic: {} using the following SQL: {}", 
                    course.getTopic(), CREATE_COURSE);
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
    public int delete(Long id) {
        try {
            LOG.debug("Trying to delete the course with id: {} using the following SQL: {}", 
                    id, DELETE_COURSE_BY_ID);
            return jdbcTemplate.update(DELETE_COURSE_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e, 
                    SQL_DELETE_COURSE_BY_ID_ERROR,
                    id);
        }
    }


    @Override
    public Course findById(Long id) {
        try {
            LOG.debug("Trying to find the course with id: {} using the following SQL: {}", 
                    id, FIND_BY_ID);
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
            LOG.debug("Trying to find all the courses using the following SQL: {}", 
                    FIND_ALL);
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
                    .withSubject(new Subject(rs.getLong("id"), 
                            rs.getInt("number"), rs.getString("name")))
                    .withTopic(rs.getString("topic")).build();
        }
    }
}

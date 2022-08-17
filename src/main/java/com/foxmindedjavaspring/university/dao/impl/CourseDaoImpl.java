package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.Utils;
import com.foxmindedjavaspring.university.dao.CourseDao;
import com.foxmindedjavaspring.university.dao.LecturerDao;
import com.foxmindedjavaspring.university.dao.SubjectDao;
import com.foxmindedjavaspring.university.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
    public static final String CREATE_COURSE = "INSERT INTO courses(topic, number_of_hours) VALUES(:topic, :number_of_hours)";
    public static final String DELETE_COURSE = "DELETE FROM courses WHERE id = :id";
    public static final String FIND_BY_ID = "SELECT * FROM courses WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM courses";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CourseMapper courseMapper;
    
    public CourseDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, CourseMapper 
            courseMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.courseMapper = courseMapper;
    }

    public int create(Course course) {
        Map<String, Object> namedParameters = new HashMap<>();   
        namedParameters.put("topic", course.getTopic());
        namedParameters.put("number_of_hours", course.getNumberOfHours());
        return jdbcTemplate.update(CREATE_COURSE, namedParameters);
    }
    
    public int delete(long id) {
        return jdbcTemplate.update(DELETE_COURSE, 
                Utils.getSingleNamed("id", id));
    }

    public Course findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, 
                Utils.getSingleNamed("id", id), courseMapper);
    }
    
    public List<Course> findAll() {
        return jdbcTemplate.query(FIND_ALL, courseMapper);
    }

    class CourseMapper implements RowMapper<Course> {
        private LecturerDao lecturerDao;
        private SubjectDao subjectDao;
    
        CourseMapper(LecturerDao lecturerDao, SubjectDao subjectDao) {
            this.lecturerDao = lecturerDao;
            this.subjectDao = subjectDao;
        }
    
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course.Builder()
                             .withDescription(rs.getString("description"))
                             .withEndDate(rs.getDate("end_date").toLocalDate())
                             .withLecturer(lecturerDao.findById(rs.getLong
                                    ("lecturer_id")))
                             .withNumberOfHours(rs.getInt("number_of_hours"))
                             .withRate(rs.getInt("rate"))
                             .withStartDate(rs.getDate("start_date")
                                    .toLocalDate())
                             .withSubject(subjectDao.findById(rs.getLong
                                    ("subject_id")))
                             .withTopic(rs.getString("topic"))
                             .build();
        }
    }
}


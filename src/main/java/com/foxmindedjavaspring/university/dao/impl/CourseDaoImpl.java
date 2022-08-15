package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
    public static final String ADD_RATE = "UPDATE courses SET rate = :rate WHERE id = :id";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CourseMapper courseMapper;
    
    public CourseDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, CourseMapper courseMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.courseMapper = courseMapper;
    }

    public Course create(Course course) {
        // List<SqlParameter> sqlParameters = new ArrayList<>();
        // sqlParameters.add(new SqlParameter(Types.VARCHAR, "topic"));
        // sqlParameters.add(new SqlParameter(Types.INTEGER, "number_of_hours"));
        // PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory
        //     (CREATE_COURSE, sqlParameters);
        // pscf.setReturnGeneratedKeys(true);
        // pscf.setGeneratedKeysColumnNames("id");
        // PreparedStatementCreator psc = pscf.newPreparedStatementCreator
        // 	(Arrays.asList(course.getTopic(), course.getNumberOfHours()));
        // // KeyHolder keyHolder = new GeneratedKeyHolder();
        // jdbcTemplate.update(psc, keyHolder);
        // long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        // return findById(id);


        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("topic", course.getTopic());
        namedParameters.addValue("number_of_hours", course.getNumberOfHours());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(CREATE_COURSE, namedParameters, keyHolder);
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(id);
    }
    
    public Course delete(Integer id) {
        jdbcTemplate.update(DELETE_COURSE, id);
        return findById(id);
    }

    private Course findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, courseMapper, id);
    }
    
    public List<Course> findAll() {
        return jdbcTemplate.query(FIND_ALL, courseMapper);
    }

    public Course addRate(long id, int rate) {
        jdbcTemplate.update(ADD_RATE, rate, id);
        return findById(id);
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
                             .withStartDate(rs.getDate("start_date").toLocalDate())
                             .withSubject(subjectDao.findById(rs.getLong
                                    ("subject_id")))
                             .withTopic(rs.getString("topic"))
                             .build();
        }
    }
}


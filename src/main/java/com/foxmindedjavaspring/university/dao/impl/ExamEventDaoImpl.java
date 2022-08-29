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
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

@Repository
public class ExamEventDaoImpl implements GenericDao<ExamEvent> {
    public static final String CREATE_EXAM_EVENT = 
              "INSERT INTO exam_events(exam_id, date, state, lab) "
            + "VALUES((SELECT id FROM exams WHERE title = :title), :date, :state, :lab)";
    public static final String DELETE_EXAM_EVENT = 
              "DELETE FROM exam_events "
            + "WHERE exam_id = (SELECT id FROM exams WHERE title = title) AND "
            + "date = :date AND "
            + "lab = :lab";
    public static final String FIND_BY_ID = 
          "SELECT "
            + "exam_events.exam_start AS exam_start, "
            + "exam_events.exam_end AS exam_end, "
            + "exam_events.state AS state, "
            + "exam_events.lab AS lab, "
            + "exam_events.rate AS rate, "
            + "exams.title AS title, "
            + "exams.description AS description "
        + "FROM "
            + "exam_events "
        + "JOIN exams "
            + "ON exam_events.exam_id = exams.id "
        + "WHERE exam_events.id = :id";
    public static final String FIND_ALL = 
          "SELECT "
            + "exam_events.exam_start AS exam_start, "
            + "exam_events.exam_end AS exam_end, "
            + "exam_events.state AS state, "
            + "exam_events.lab AS lab, "
            + "exam_events.rate AS rate, "
            + "exams.title AS title, "
            + "exams.description AS description "
        + "FROM "
            + "exam_events "
        + "JOIN exams "
            + "ON exam_events.exam_id = exams.id";
    public static final String SQL_CREATE_EXAM_EVENT_ERROR = " :: Error while creating the exam event with title: {}";
    public static final String SQL_DELETE_EXAM_EVENT_ERROR = " :: Error while deleting the exam event with id: {}";
    public static final String SQL_FIND_EXAM_EVENT_ERROR = " :: Error while searching the exam event with id: {}";
    public static final String SQL_FIND_ALL_EXAM_EVENTS_ERROR = " :: Error while searching all exam events.";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ExamEventDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(ExamEvent examEvent) {
        try {
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("title", examEvent.getExam().getTitle());
            namedParameters.put("state", examEvent.getState());
            namedParameters.put("lab", examEvent.getLab());
            namedParameters.put("exam_start", examEvent.getStartTime());
            namedParameters.put("exam_end", examEvent.getEndTime());
            namedParameters.put("rate", examEvent.getRate());
            return jdbcTemplate.update(CREATE_EXAM_EVENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_EXAM_EVENT_ERROR,
                    examEvent.getExam().getTitle());
        }
    }

    @Override
    public int delete(long id) {
        try {
            return jdbcTemplate.update(DELETE_EXAM_EVENT,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_EXAM_EVENT_ERROR, id);
        }
    }

    @Override
    public ExamEvent findById(long id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    Collections.singletonMap("id", id),
                    new ExamEventMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_EXAM_EVENT_ERROR, id);
        }
    }

    @Override
    public List<ExamEvent> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new ExamEventMapper());
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_FIND_ALL_EXAM_EVENTS_ERROR);
        }
    }

    static class ExamEventMapper implements RowMapper<ExamEvent> {
        @Override
        public ExamEvent mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            return new ExamEvent.Builder()
                    .withExam(new Exam(rs.getString("title"),
                            rs.getString("description")))
                    .withLab(rs.getInt("lab"))
                    .withEndTime(rs.getTimestamp("exam_end")
                            .toLocalDateTime())
                    .withStartTime(rs.getTimestamp("exam_start")
                            .toLocalDateTime())
                    .withState(ExamState.valueOf(rs.getString("state")))
                    .withRate(rs.getInt("rate")).build();
        }
    }
}

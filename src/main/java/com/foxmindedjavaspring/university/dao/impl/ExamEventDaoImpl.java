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
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

@Repository
public class ExamEventDaoImpl implements GenericDao<ExamEvent> {
    static final String CREATE_EXAM_EVENT = 
              "INSERT INTO exam_events(exam_id, date, state, lab) "
            + "VALUES((SELECT id FROM exams WHERE title = :title), :exam_start, :exam_end, :state, :lab, :rate)";
    static final String DELETE_EXAM_EVENT_BY_ID = 
              "DELETE FROM exam_events "
            + "WHERE id = :id";
    static final String FIND_BY_ID = 
          "SELECT "
            + "ee.exam_start AS exam_start, "
            + "ee.exam_end AS exam_end, "
            + "ee.state AS state, "
            + "ee.lab AS lab, "
            + "ee.rate AS rate, "
            + "ex.title AS title, "
            + "ex.description AS description "
        + "FROM "
            + "exam_events ee"
        + "JOIN exams ex "
            + "ON ee.exam_id = ex.id "
        + "WHERE ee.id = :id";
    static final String FIND_ALL = 
          "SELECT "
            + "ee.exam_start AS exam_start, "
            + "ee.exam_end AS exam_end, "
            + "ee.state AS state, "
            + "ee.lab AS lab, "
            + "ee.rate AS rate, "
            + "ex.title AS title, "
            + "ex.description AS description "
        + "FROM "
            + "exam_events ee "
        + "JOIN exams ex "
            + "ON ee.exam_id = ex.id";
    static final String SQL_CREATE_EXAM_EVENT_ERROR = " :: Error while creating the exam event with title: {}";
    static final String SQL_DELETE_EXAM_EVENT_BY_ID_ERROR = " :: Error while deleting the exam event with id: {}";
    static final String SQL_FIND_EXAM_EVENT_ERROR = " :: Error while searching the exam event with id: {}";
    static final String SQL_FIND_ALL_EXAM_EVENTS_ERROR = " :: Error while searching all exam events.";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(
                ExamEventDaoImpl.class);

    public ExamEventDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(ExamEvent examEvent) {
        try {
            LOG.debug("Trying to create the exam event with title: {} using the following SQL: {}", 
                    examEvent.getExam().getTitle(), CREATE_EXAM_EVENT);
            Map<String, Object> namedParameters = Map.of(
                    "title", examEvent.getExam().getTitle(),
                    "state", examEvent.getState(),
                    "lab", examEvent.getLab(),
                    "exam_start", examEvent.getStartTime(),
                    "exam_end", examEvent.getEndTime(),
                    "rate", examEvent.getRate());
            return jdbcTemplate.update(CREATE_EXAM_EVENT, namedParameters);
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_CREATE_EXAM_EVENT_ERROR,
                    examEvent.getExam().getTitle());
        }
    }

    @Override
    public int delete(Long id) {
        try {
            LOG.debug("Trying to delete the exam event with id: {} using the following SQL: {}", 
                    id, DELETE_EXAM_EVENT_BY_ID);
            return jdbcTemplate.update(DELETE_EXAM_EVENT_BY_ID,
                    Collections.singletonMap("id", id));
        } catch (Exception e) {
            throw new UniversityDataAcessException(e,
                    SQL_DELETE_EXAM_EVENT_BY_ID_ERROR, id);
        }
    }

    @Override
    public ExamEvent findById(Long id) {
        try {
            LOG.debug("Trying to find the exam event with id: {} using the following SQL: {}", 
                    id, FIND_BY_ID);
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
            LOG.debug("Trying to find all the exam events using the following SQL: {}", 
                    FIND_ALL);
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

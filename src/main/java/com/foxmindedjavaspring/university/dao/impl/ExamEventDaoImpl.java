package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.ExamEventDao;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

@Component
public class ExamEventDaoImpl implements ExamEventDao {
    private static final String ADD_EXAM_EVENT = "INSERT INTO exam_events(exam_id, date, state, lab) VALUES((SELECT id FROM exams WHERE title = ?),?, ?, ?)";
    private static final String REMOVE_EXAM_EVENT = "DELETE FROM exam_events WHERE exam_id = (SELECT id FROM exams WHERE title = ?) AND date = ? AND lab = ?";
    private static final String SET_STATE = "UPDATE exam_events SET state = ? WHERE exam_id = (SELECT id FROM exams WHERE title = ?) AND date = ? AND lab = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExamEventDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(ExamEvent examEvent) {
        jdbcTemplate.update(ADD_EXAM_EVENT, examEvent.getExam().getTitle(),
                examEvent.getDate(), examEvent.getState(),
                examEvent.getLab());
    }

    @Override
    public void delete(ExamEvent examEvent) {
        jdbcTemplate.update(REMOVE_EXAM_EVENT, examEvent.getExam().getTitle(),
                examEvent.getDate(), examEvent.getLab());
    }

    @Override
    public void setState(ExamEvent examEvent, ExamState examState) {
        jdbcTemplate.update(SET_STATE, examState, examEvent.getExam().getTitle(),
                examEvent.getDate(), examEvent.getLab());
    }
}

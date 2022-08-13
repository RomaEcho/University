package com.foxmindedjavaspring.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.ExamEventDao;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

@Component
public class ExamEventDaoImpl implements ExamEventDao {
    public static final String ADD_EXAM_EVENT = "INSERT INTO exam_events(exam_id, date, state, lab) VALUES((SELECT id FROM exams WHERE title = ?),?, ?, ?)";
    public static final String REMOVE_EXAM_EVENT = "DELETE FROM exam_events WHERE exam_id = (SELECT id FROM exams WHERE title = ?) AND date = ? AND lab = ?";
    public static final String SET_STATE = "UPDATE exam_events SET state = ? WHERE exam_id = (SELECT id FROM exams WHERE title = ?) AND date = ? AND lab = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExamEventDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(ExamEvent examEvent) {
        return jdbcTemplate.update(ADD_EXAM_EVENT, examEvent.getExam().getTitle(),
                examEvent.getDate(), examEvent.getState(),
                examEvent.getLab()) == 1;
    }

    @Override
    public boolean delete(ExamEvent examEvent) {
        return jdbcTemplate.update(REMOVE_EXAM_EVENT, examEvent.getExam().getTitle(),
                examEvent.getDate(), examEvent.getLab()) == 1;
    }

    @Override
    public boolean setState(ExamEvent examEvent, ExamState examState) {
        return jdbcTemplate.update(SET_STATE, examState, examEvent.getExam().getTitle(),
                examEvent.getDate(), examEvent.getLab()) == 1;
    }
}

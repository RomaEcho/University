package com.foxmindedjavaspring.university.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.dao.ExamEventDao;
import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;
import com.foxmindedjavaspring.university.utils.Utils;

@Repository
public class ExamEventDaoImpl implements ExamEventDao<ExamEvent> {
	public static final String CREATE_EXAM_EVENT = "INSERT INTO exam_events(exam_id, date, state, lab) VALUES((SELECT id FROM exams WHERE title = :title), :date, :state, :lab)";
	public static final String DELETE_EXAM_EVENT = "DELETE FROM exam_events WHERE exam_id = (SELECT id FROM exams WHERE title = title) AND date = :date AND lab = :lab";
	public static final String FIND_BY_ID = "SELECT * FROM exam_events WHERE id = :id";
	public static final String FIND_ALL = "SELECT * FROM exam_events";
	private static final String SQL_CREATE_EXAM_EVENT_ERROR = " :: Error while creating the exam event with";
	private static final String SQL_DELETE_EXAM_EVENT_ERROR =  " :: Error while deleting the exam event with id:";
	private static final String SQL_FIND_EXAM_EVENT_ERROR = " :: Error while searching the exam event with id:";
	private static final String SQL_FIND_ALL_EXAM_EVENTS_ERROR = " :: Error while searching all exam events.";
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final ExamEventMapper examEventMapper;

	public ExamEventDaoImpl(NamedParameterJdbcTemplate jdbcTemplate,
			ExamEventMapper examEventMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.examEventMapper = examEventMapper;
	}

	@Override
	public int create(ExamEvent examEvent) {
		try {
		    Map<String, Object> namedParameters = new HashMap<>();
		    namedParameters.put("title", examEvent.getExam().getTitle());
		    namedParameters.put("date", examEvent.getDate());
		    namedParameters.put("state", examEvent.getState());
		    namedParameters.put("lab", examEvent.getLab());
		    return jdbcTemplate.update(CREATE_EXAM_EVENT, namedParameters);
		} catch (Exception e) {
		    throw new UniversityDataAcessException(
					String.format("%s title: %s, date: %s",
                    	SQL_CREATE_EXAM_EVENT_ERROR, 
						examEvent.getExam().getTitle(),
						examEvent.getDate().toString()), 
					e);
		}
	}

	@Override
	public int delete(long id) {
		try {
		    return jdbcTemplate.update(DELETE_EXAM_EVENT,
		    		Utils.getMapSinglePair("id", id));
		} catch (Exception e) {
		    throw new UniversityDataAcessException(
                    SQL_DELETE_EXAM_EVENT_ERROR + id, e);
		}
	}

	@Override
	public ExamEvent findById(long id) {
		try {
		    return jdbcTemplate.queryForObject(FIND_BY_ID,
		    		Utils.getMapSinglePair("id", id), examEventMapper);
		} catch (Exception e) {
		    throw new UniversityDataAcessException(
                    SQL_FIND_EXAM_EVENT_ERROR + id, e);
		}
	}

	@Override
	public List<ExamEvent> findAll() {
		try {
		    return jdbcTemplate.query(FIND_ALL, examEventMapper);
		} catch (Exception e) {
		    throw new UniversityDataAcessException(
                    SQL_FIND_ALL_EXAM_EVENTS_ERROR, e);
		}
	}

	class ExamEventMapper implements RowMapper<ExamEvent> {
		private ExamDao examDao;

		ExamEventMapper(ExamDao examDao) {
			this.examDao = examDao;
		}

		@Override
		public ExamEvent mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			return new ExamEvent.Builder()
					.withDate(rs.getDate("date").toLocalDate())
					.withExam((Exam) examDao.findById(rs.getLong("exam_id")))
					.withLab(rs.getInt("lab"))
					.withEndTime(rs.getTime("exam_end").toLocalTime())
					.withStartTime(rs.getTime("exam_start").toLocalTime())
					.withState(ExamState.valueOf(rs.getString("state")))
					.withRate(rs.getInt("rate")).build();
		}
	}
}

package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.ExamEventDao;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.service.ExamEventService;

@Component
public class ExamEventServiceImpl implements ExamEventService {
    private final ExamEventDao examEventDao;

    public ExamEventServiceImpl(ExamEventDao examEventDao) {
        this.examEventDao = examEventDao;
    }

    @Override
    public void addExamEvent(ExamEvent examEvent) {
        examEventDao.create(examEvent);
    }

    @Override
    public void removeExamEvent(ExamEvent examEvent) {
        examEventDao.delete(examEvent);
    }

    @Override
    public ExamEvent getExamEvent(Long id) {
        return examEventDao.findById(id);
    }

    @Override
    public List<ExamEvent> getAllExamEvents() {
        return examEventDao.findAll();
    }

    @Override
    public void editExamEvent(ExamEvent examEvent) {
        examEventDao.update(examEvent);
    }
}

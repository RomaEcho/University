package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.service.ExamEventService;

@Component
public class ExamEventServiceImpl implements ExamEventService {
    private final GenericDao<ExamEvent> genericDao;

    public ExamEventServiceImpl(GenericDao<ExamEvent> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addExamEvent(ExamEvent examEvent) {
        genericDao.create(examEvent);
    }

    @Override
    public void removeExamEvent(Long id) {
        genericDao.delete(id);
    }

    @Override
    public ExamEvent getExamEvent(Long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<ExamEvent> getAllExamEvents() {
        return genericDao.findAll();
    }
}

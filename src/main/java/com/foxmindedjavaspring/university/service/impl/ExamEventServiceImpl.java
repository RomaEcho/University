package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.service.ExamEventService;

@Component
public class ExamEventServiceImpl implements ExamEventService {
    private final GenericDao genericDao;

    public ExamEventServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addExamEvent(ExamEvent examEvent) {
        genericDao.create(examEvent);
    }

    @Override
    public void removeExamEvent(ExamEvent examEvent) {
        genericDao.delete(examEvent);

    }

    @Override
    public List<ExamEvent> getAllExamEvents() {
        return genericDao.findAll();
    }
}

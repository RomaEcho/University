package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class ExamEventServiceImpl implements GenericService<ExamEvent> {
    private final GenericDao genericDao;

    public ExamEventServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(ExamEvent examEvent) {
        genericDao.create(examEvent);
    }

    @Override
    public void remove(ExamEvent examEvent) {
        genericDao.delete(examEvent);

    }

    @Override
    public List<ExamEvent> getAll() {
        return genericDao.findAll();
    }
}

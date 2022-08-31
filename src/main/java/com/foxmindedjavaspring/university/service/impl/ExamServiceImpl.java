package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class ExamServiceImpl implements GenericService<Exam> {
    private final GenericDao genericDao;

    public ExamServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(Exam exam) {
        genericDao.create(exam);
    }

    @Override
    public void remove(Exam exam) {
        genericDao.delete(exam);
    }

    @Override
    public List<Exam> getAll() {
        return genericDao.findAll();
    }

}

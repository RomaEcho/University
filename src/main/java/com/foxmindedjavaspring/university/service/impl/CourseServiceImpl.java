package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class CourseServiceImpl implements GenericService<Course> {
    private final GenericDao genericDao;

    public CourseServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(Course course) {
        genericDao.create(course);
    }

    @Override
    public void remove(Course course) {
        genericDao.delete(course);
    }

    @Override
    public List<Course> getAll() {
        return genericDao.findAll();
    }
}

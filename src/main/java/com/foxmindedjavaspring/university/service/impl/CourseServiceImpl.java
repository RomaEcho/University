package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.service.CourseService;

@Component
public class CourseServiceImpl implements CourseService {
    private final GenericDao genericDao;

    public CourseServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addCourse(Course course) {
        genericDao.create(course);
    }

    @Override
    public void removeCourse(Course course) {
        genericDao.delete(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return genericDao.findAll();
    }
}

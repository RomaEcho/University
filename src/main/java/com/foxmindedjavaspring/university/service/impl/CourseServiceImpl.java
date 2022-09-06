package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.service.CourseService;

@Component
public class CourseServiceImpl implements CourseService {
    private final GenericDao<Course> genericDao;

    public CourseServiceImpl(GenericDao<Course> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addCourse(Course course) {
        genericDao.create(course);
    }

    @Override
    public void removeCourse(long id) {
        genericDao.delete(id);
    }

    @Override
    public Course getCourse(long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return genericDao.findAll();
    }
}

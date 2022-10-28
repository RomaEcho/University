package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.CourseDao;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.service.CourseService;

@Component
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void addCourse(Course course) {
        courseDao.create(course);
    }

    @Override
    public void removeCourse(Course course) {
        courseDao.delete(course);
    }

    @Override
    public Course getCourse(Long id) {
        return courseDao.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public void editCourse(Course course) {
        courseDao.update(course);
    }
}

package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Course;

public interface CourseService {

    void addCourse(Course course);

    void removeCourse(Long id);

    Course getCourse(Long id);

    List<Course> getAllCourses();

}
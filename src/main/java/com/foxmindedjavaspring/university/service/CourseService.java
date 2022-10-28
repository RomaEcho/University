package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Course;

public interface CourseService {

    void addCourse(Course course);

    void removeCourse(Course course);

    Course getCourse(Long id);

    List<Course> getAllCourses();

    void editCourse(Course course);
}
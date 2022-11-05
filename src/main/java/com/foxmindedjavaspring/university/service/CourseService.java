package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Course;

import java.util.List;

public interface CourseService {

    void addCourse(Course course);

    void editCourse(Course course);

    void removeCourse(Course course);

    Course getCourse(Long id);

    List<Course> getAllCourses();

}
package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Course;

public interface CourseService {

    void addCourse(Course course);

    void removeCourse(long id);

    Course getCourse(long id);

    List<Course> getAllCourses();

}
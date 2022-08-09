package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Course;

public interface CourseDao {
    void addCourse(Course course);

    void removeCourse(Course course);

    void addRate(Course course, int rate);

}

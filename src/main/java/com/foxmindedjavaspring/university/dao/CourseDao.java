package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Course;

public interface CourseDao {
    void create(Course course);

    void delete(Course course);

    void addRate(Course course, int rate);

}

package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Course;

public interface CourseDao {
    boolean create(Course course);

    boolean delete(Course course);

    boolean addRate(Course course, int rate);

}

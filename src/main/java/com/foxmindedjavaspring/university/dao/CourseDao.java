package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.Course;

@Repository
public class CourseDao extends AbstractGenericDao<Course> {
    public CourseDao() {
        super(Course.class);
    }
}

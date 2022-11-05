package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.CourseRepository;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.service.CourseService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {
    static final String GET_COURSE_ERROR = "::Error while getting course with id";
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void removeCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_COURSE_ERROR, id)));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void editCourse(Course course) {
        courseRepository.save(course);
    }

}

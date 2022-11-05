package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Student;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);

    void removeStudent(Student student);

    void editStudent(Student student);

    Student getStudent(Long id);

    List<Student> getAllStudents();
}
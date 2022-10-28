package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Student;

public interface StudentService {

    void addStudent(Student student);

    void removeStudent(Student student);

    Student getStudent(Long id);

    List<Student> getAllStudents();

    void editStudent(Student student);
}
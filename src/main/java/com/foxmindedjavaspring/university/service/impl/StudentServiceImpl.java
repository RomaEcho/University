package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.StudentRepository;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.service.StudentService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {
    static final String GET_STUDENT_ERROR = "::Error while getting student with id";
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(@NonNull Student student) {
        studentRepository.save(student);
    }

    @Override
    public void removeStudent(@NonNull Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_STUDENT_ERROR, id)));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void editStudent(@NonNull Student student) {
        studentRepository.save(student);
    }
}

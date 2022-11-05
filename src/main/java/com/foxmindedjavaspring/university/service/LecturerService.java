package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Lecturer;

import java.util.List;

public interface LecturerService {

    void addLecturer(Lecturer lecturer);

    void removeLecturer(Lecturer lecturer);

    void editLecturer(Lecturer lecturer);

    Lecturer getLecturer(Long id);

    List<Lecturer> getAllLecturers();
}
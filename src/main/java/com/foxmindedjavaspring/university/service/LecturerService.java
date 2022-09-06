package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Lecturer;

public interface LecturerService {

    void addLecturer(Lecturer lecturer);

    void removeLecturer(Long id);

    Lecturer getLecturer(Long id);

    List<Lecturer> getAllLecturers();

}
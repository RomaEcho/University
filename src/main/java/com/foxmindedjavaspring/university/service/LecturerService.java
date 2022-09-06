package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Lecturer;

public interface LecturerService {

    void addLecturer(Lecturer lecturer);

    void removeLecturer(long id);

    Lecturer getLecturer(long id);

    List<Lecturer> getAllLecturers();

}
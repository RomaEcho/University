package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Lecturer;

public interface LecturerService {

    void addLecturer(Lecturer lecturer);

    void removeLecturer(Lecturer lecturer);

    List<Lecturer> getAllLecturers();

}
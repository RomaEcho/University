package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.University;

import java.util.List;

public interface UniversityService {

    void addUniversity(University university);

    void removeUniversity(University university);

    void editUniversity(University university);

    University getUniversity(Long id);

    List<University> getAllUniversities();
}
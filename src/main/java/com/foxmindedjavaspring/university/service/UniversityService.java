package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.University;

public interface UniversityService {

    void addUniversity(University university);

    void removeUniversity(long id);

    University getUniversity(long id);

    List<University> getAllUniversities();

}
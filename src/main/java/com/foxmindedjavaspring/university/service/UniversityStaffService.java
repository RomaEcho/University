package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.UniversityStaff;

public interface UniversityStaffService {

    void addUniversityStaff(UniversityStaff universityStaff);

    void removeUniversityStaff(Long id);

    UniversityStaff getUniversityStaff(Long id);

    List<UniversityStaff> getAllUniversityStaff();

}
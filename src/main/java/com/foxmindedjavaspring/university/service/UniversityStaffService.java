package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.UniversityStaff;

public interface UniversityStaffService {

    void addUniversityStaff(UniversityStaff universityStaff);

    void removeUniversityStaff(long id);

    UniversityStaff getUniversityStaff(long id);

    List<UniversityStaff> getAllUniversityStaff();

}
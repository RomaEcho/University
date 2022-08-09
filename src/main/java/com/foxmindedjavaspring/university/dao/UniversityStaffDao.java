package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.UniversityStaff;

public interface UniversityStaffDao {
    void addUniversityStaff(UniversityStaff universityStaff);

    void removeUniversityStaff(UniversityStaff universityStaff);

    void updateTitle(UniversityStaff universityStaff, String title);
}

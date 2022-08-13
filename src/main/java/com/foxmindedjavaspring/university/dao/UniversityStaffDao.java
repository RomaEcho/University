package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.UniversityStaff;

public interface UniversityStaffDao {
    void create(UniversityStaff universityStaff);

    void delete(UniversityStaff universityStaff);

    void updateTitle(UniversityStaff universityStaff, String title);
}

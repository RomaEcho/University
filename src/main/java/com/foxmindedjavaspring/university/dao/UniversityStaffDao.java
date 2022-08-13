package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.UniversityStaff;

public interface UniversityStaffDao {
    boolean create(UniversityStaff universityStaff);

    boolean delete(UniversityStaff universityStaff);

    boolean updateTitle(UniversityStaff universityStaff, String title);
}

package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Lecturer;

public interface LecturerDao {
    boolean create(Lecturer lecturer);

    boolean delete(Lecturer lecturer);

    boolean updateLevel(Lecturer lecturer, String level);

    Lecturer findById(long long1);
}

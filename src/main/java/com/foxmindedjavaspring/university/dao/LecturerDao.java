package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Lecturer;

public interface LecturerDao {
    void create(Lecturer lecturer);

    void delete(Lecturer lecturer);

    void updateLevel(Lecturer lecturer, String level);
}

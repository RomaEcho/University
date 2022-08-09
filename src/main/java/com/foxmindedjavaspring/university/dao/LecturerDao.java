package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Lecturer;

public interface LecturerDao {
    void addLecturer(Lecturer lecturer);

    void removeLecturer(Lecturer lecturer);

    void updateLevel(Lecturer lecturer, String level);
}

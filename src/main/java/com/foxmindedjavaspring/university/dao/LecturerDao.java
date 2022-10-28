package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.Lecturer;

@Repository
public class LecturerDao extends AbstractGenericDao<Lecturer> {
    public LecturerDao() {
        super(Lecturer.class);
    }
}

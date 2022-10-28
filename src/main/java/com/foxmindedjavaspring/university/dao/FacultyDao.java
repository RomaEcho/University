package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.Faculty;

@Repository
public class FacultyDao extends AbstractGenericDao<Faculty> {
    public FacultyDao() {
        super(Faculty.class);
    }
}

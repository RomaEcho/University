package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.University;

@Repository
public class UniversityDao extends AbstractGenericDao<University> {
    public UniversityDao() {
        super(University.class);
    }
}

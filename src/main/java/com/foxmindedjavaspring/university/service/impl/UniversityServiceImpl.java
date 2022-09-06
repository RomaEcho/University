package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.service.UniversityService;

@Component
public class UniversityServiceImpl implements UniversityService {
    private final GenericDao<University> genericDao;

    public UniversityServiceImpl(GenericDao<University> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addUniversity(University university) {
        genericDao.create(university);
    }

    @Override
    public void removeUniversity(Long id) {
        genericDao.delete(id);
    }

    @Override
    public University getUniversity(Long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<University> getAllUniversities() {
        return genericDao.findAll();
    }
}

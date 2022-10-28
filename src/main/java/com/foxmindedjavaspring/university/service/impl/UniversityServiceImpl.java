package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.UniversityDao;
import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.service.UniversityService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UniversityServiceImpl implements UniversityService {
    private final UniversityDao universityDao;

    public UniversityServiceImpl(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    @Override
    public void addUniversity(University university) {
        universityDao.create(university);
    }

    @Override
    public void removeUniversity(University university) {
        universityDao.delete(university);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public University getUniversity(Long id) {
        return universityDao.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<University> getAllUniversities() {
        return universityDao.findAll();
    }

    @Override
    public void editUniversity(University university) {
        universityDao.update(university);
    }
}

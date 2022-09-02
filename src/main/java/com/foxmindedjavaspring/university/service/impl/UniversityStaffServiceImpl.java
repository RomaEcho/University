package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.UniversityStaff;
import com.foxmindedjavaspring.university.service.UniversityStaffService;

@Component
public class UniversityStaffServiceImpl implements UniversityStaffService {
    private final GenericDao genericDao;

    public UniversityStaffServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addUniversityStaff(UniversityStaff universityStaff) {
        genericDao.create(universityStaff);
    }

    @Override
    public void removeUniversityStaff(UniversityStaff universityStaff) {
        genericDao.delete(universityStaff);
    }

    @Override
    public List<UniversityStaff> getAllUniversityStaff() {
        return genericDao.findAll();
    }
}

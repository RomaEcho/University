package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.UniversityStaff;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class UniversityStaffServiceImpl implements 
        GenericService<UniversityStaff> {
    private final GenericDao genericDao;

    public UniversityStaffServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(UniversityStaff universityStaff) {
        genericDao.create(universityStaff);
    }

    @Override
    public void remove(UniversityStaff universityStaff) {
        genericDao.delete(universityStaff);
    }

    @Override
    public List<UniversityStaff> getAll() {
        return genericDao.findAll();
    }
}

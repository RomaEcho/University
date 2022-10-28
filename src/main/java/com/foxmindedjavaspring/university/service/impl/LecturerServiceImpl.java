package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.LecturerDao;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.service.LecturerService;

@Component
public class LecturerServiceImpl implements LecturerService {
    private final LecturerDao lecturerDao;

    public LecturerServiceImpl(LecturerDao lecturerDao) {
        this.lecturerDao = lecturerDao;
    }

    @Override
    public void addLecturer(Lecturer lecturer) {
        lecturerDao.create(lecturer);
    }

    @Override
    public void removeLecturer(Lecturer lecturer) {
        lecturerDao.delete(lecturer);
    }

    @Override
    public Lecturer getLecturer(Long id) {
        return lecturerDao.findById(id);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return lecturerDao.findAll();
    }

    @Override
    public void editLecturer(Lecturer lecturer) {
        lecturerDao.update(lecturer);
    }
}

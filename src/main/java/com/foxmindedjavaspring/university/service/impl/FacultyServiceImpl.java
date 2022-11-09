package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.FacultyRepository;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.service.FacultyService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacultyServiceImpl implements FacultyService {
    static final String GET_FACULTY_ERROR = "::Error while getting faculty with id";
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void addFaculty(@NonNull Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(@NonNull Faculty faculty) {
        facultyRepository.delete(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_FACULTY_ERROR, id)));
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public void editFaculty(@NonNull Faculty faculty) {
        facultyRepository.save(faculty);
    }
}

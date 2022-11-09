package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.UniversityRepository;
import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.service.UniversityService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UniversityServiceImpl implements UniversityService {
    static final String GET_UNIVERSITY_ERROR = "::Error while getting university with id";
    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public void addUniversity(@NonNull University university) {
        universityRepository.save(university);
    }

    @Override
    public void removeUniversity(@NonNull University university) {
        universityRepository.delete(university);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public University getUniversity(Long id) {
        return universityRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_UNIVERSITY_ERROR, id)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public void editUniversity(@NonNull University university) {
        universityRepository.save(university);
    }
}

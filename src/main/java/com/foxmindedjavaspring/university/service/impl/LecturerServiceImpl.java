package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.LecturerRepository;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.service.LecturerService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LecturerServiceImpl implements LecturerService {
    static final String GET_LECTURER_ERROR = "::Error while getting lecturer with id";
    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public void addLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    @Override
    public void removeLecturer(Lecturer lecturer) {
        lecturerRepository.delete(lecturer);
    }

    @Override
    public Lecturer getLecturer(Long id) {
        return lecturerRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_LECTURER_ERROR, id)));
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    @Override
    public void editLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }
}

package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.SubjectRepository;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class SubjectServiceImpl implements SubjectService {
    static final String GET_SUBJECT_ERROR = "Error while getting subject with id";
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void removeSubject(Subject subject) {
        subjectRepository.delete(subject);
    }

    @Override
    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException(String.format("%s: %s",
                            GET_SUBJECT_ERROR, id)));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> getByName(String name) {
        return subjectRepository.findByName(name);
    }

    @Override
    public void editSubject(Subject subject) {
        subjectRepository.save(subject);
    }
}

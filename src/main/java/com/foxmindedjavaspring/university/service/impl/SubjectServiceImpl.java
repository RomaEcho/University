package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.mapper.SubjectMapper;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.repository.SubjectRepository;
import com.foxmindedjavaspring.university.service.SubjectService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class SubjectServiceImpl implements SubjectService {
    static final String GET_SUBJECT_ERROR = "Error while getting subject with id";
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public void addSubject(@NonNull Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void addSubject(@NonNull SubjectDto subjectDto) {
        subjectRepository.save(subjectMapper.apply(subjectDto));
    }

    @Override
    public void removeSubject(@NonNull Subject subject) {
        subjectRepository.delete(subject);
    }

    @Override
    public void removeSubject(@NonNull SubjectDto subjectDto) {
        subjectRepository.delete(subjectMapper.apply(subjectDto));
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
    public void editSubject(@NonNull Subject subject) {
        subjectRepository.save(subject);
    }
}

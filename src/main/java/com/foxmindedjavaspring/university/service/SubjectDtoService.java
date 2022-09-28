package com.foxmindedjavaspring.university.service;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;

@Component
public class SubjectDtoService {
    public Subject convertToSubject(SubjectDto subjectDto) {
        Subject subject = new Subject(subjectDto.getId(), subjectDto.getNumber(),
                subjectDto.getName());
        subject.setDescription(subjectDto.getDescription());
        return subject;
    }
}

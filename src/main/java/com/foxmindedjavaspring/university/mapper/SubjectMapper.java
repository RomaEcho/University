package com.foxmindedjavaspring.university.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;

@Component
public class SubjectMapper implements Function<SubjectDto, Subject> {
    @Override
    public Subject apply(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setNumber(subjectDto.getNumber());
        subject.setName(subjectDto.getName());
        subject.setDescription(subjectDto.getDescription());
        return subject;
    }
}

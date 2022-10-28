package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.ExamEvent;

public interface ExamEventService {

    void addExamEvent(ExamEvent examEvent);

    void removeExamEvent(ExamEvent examEvent);

    ExamEvent getExamEvent(Long id);

    List<ExamEvent> getAllExamEvents();

    void editExamEvent(ExamEvent examEvent);
}
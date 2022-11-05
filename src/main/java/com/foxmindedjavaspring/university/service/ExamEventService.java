package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.ExamEvent;

import java.util.List;

public interface ExamEventService {

    void addExamEvent(ExamEvent examEvent);

    void removeExamEvent(ExamEvent examEvent);

    void editExamEvent(ExamEvent examEvent);

    ExamEvent getExamEvent(Long id);

    List<ExamEvent> getAllExamEvents();

}
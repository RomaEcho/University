package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.ExamEvent;

public interface ExamEventService {

    void addExamEvent(ExamEvent examEvent);

    void removeExamEvent(long id);

    ExamEvent getExamEvent(long id);

    List<ExamEvent> getAllExamEvents();

}
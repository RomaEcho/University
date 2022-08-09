package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

public interface ExamEventDao {
    void addExamEvent(ExamEvent examEvent);

    void removeExamEvent(ExamEvent examEvent);

    void setState(ExamEvent examEvent, ExamState examState);

    void setRate(ExamEvent examEvent, int rate);
}

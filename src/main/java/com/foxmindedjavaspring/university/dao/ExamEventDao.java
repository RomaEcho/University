package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

public interface ExamEventDao {
    void create(ExamEvent examEvent);

    void delete(ExamEvent examEvent);

    void setState(ExamEvent examEvent, ExamState examState);

}

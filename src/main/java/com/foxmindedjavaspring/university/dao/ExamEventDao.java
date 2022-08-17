package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.model.ExamState;

public interface ExamEventDao {
    int create(ExamEvent examEvent);

    boolean delete(ExamEvent examEvent);

    boolean setState(ExamEvent examEvent, ExamState examState);

}

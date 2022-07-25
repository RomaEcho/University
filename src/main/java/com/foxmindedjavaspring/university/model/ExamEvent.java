package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class ExamEvent {
    private final Exam exam;
    private LocalDate date;
    private ExamState state;
    private Integer lab;
    private Integer rate;

    public ExamEvent(Exam exam, LocalDate date, Integer lab) {
        this.exam = exam;
        this.date = date;
        this.lab = lab;
    }

    public Exam getExam() {
        return exam;
    }

    public LocalDate getDate() {
        return date;
    }

    public ExamState getStatus() {
        return state;
    }

    public Integer getLab() {
        return lab;
    }

    public Integer getRate() {
        return rate;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(ExamState status) {
        this.state = status;
    }

    public void setLab(Integer lab) {
        this.lab = lab;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}

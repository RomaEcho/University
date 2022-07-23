package com.foxmindedjavaspring.model;

import java.time.LocalDate;

public class ExamEvent {
    private final Exam exam;
    private LocalDate date;
    private ExamState status;
    private Integer lab;
    private Integer rate;

    private ExamEvent(Builder builder) {
        this.exam = builder.exam;
        this.date = builder.date;
        this.status = builder.status;
        this.lab = builder.lab;
        this.rate = builder.rate;
    }

    public static final class Builder {
        private Exam exam;
        private LocalDate date;
        private ExamState status;
        private Integer lab;
        private Integer rate;

        public Builder() {
        }

        public Builder withExam(Exam exam) {
            this.exam = exam;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withStatus(ExamState status) {
            this.status = status;
            return this;
        }

        public Builder withLab(Integer lab) {
            this.lab = lab;
            return this;
        }

        public Builder withRate(Integer rate) {
            this.rate = rate;
            return this;
        }

        public ExamEvent build() {
            return new ExamEvent(this);
        }
    }

    public Exam getExam() {
        return exam;
    }

    public LocalDate getDate() {
        return date;
    }

    public ExamState getStatus() {
        return status;
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
        this.status = status;
    }

    public void setLab(Integer lab) {
        this.lab = lab;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}

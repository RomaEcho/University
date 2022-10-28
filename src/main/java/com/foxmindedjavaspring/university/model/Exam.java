package com.foxmindedjavaspring.university.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam extends BaseEntity {

    @OneToMany(mappedBy = "exam")
    private Set<ExamEvent> examEvents = new HashSet<>();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Set<ExamEvent> getExamEvents() {
        return examEvents;
    }

    public void setExamEvents(Set<ExamEvent> examEvents) {
        this.examEvents = examEvents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

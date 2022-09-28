package com.foxmindedjavaspring.university.dto;

import com.foxmindedjavaspring.university.model.Subject;

public class SubjectDto {
    private Long id;
    private Integer number;
    private String name;
    private String description;

    public SubjectDto() {
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject convertToSubject(){
        Subject subject = new Subject(this.getId(), this.getNumber(), 
                this.getName());
        subject.setDescription(this.getDescription());
        return subject;
    }

}
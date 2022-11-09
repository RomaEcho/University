package com.foxmindedjavaspring.university.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubjectDto {
    private Long id;
    @NotNull(message= "Number cannot not be empty")
    @Min(value = 1, message = "Number should be greater than 0")
    private Integer number;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 1, max = 20, message = "Name should be between 1 and 20 characters")
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

}
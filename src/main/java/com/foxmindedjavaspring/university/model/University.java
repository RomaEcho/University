package com.foxmindedjavaspring.university.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "universities")
public class University extends BaseEntity {

    @OneToMany(mappedBy = "university")
    private Set<Faculty> faculties;

    @Column(name = "name")
    private String name;

    @Column(name = "hq_location")
    private String hqLocation;

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHqLocation() {
        return hqLocation;
    }

    public void setHqLocation(String hqLocation) {
        this.hqLocation = hqLocation;
    }

}

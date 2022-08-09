package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Person;

public interface PersonDao {
    void addPerson(Person person);

    void removePerson(Person person);

    void updatePhoneNumber(Person person, String phone);
}

package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Person;

public interface PersonDao {
    void create(Person person);

    void delete(Person person);

    void updatePhoneNumber(Person person, String phone);
}

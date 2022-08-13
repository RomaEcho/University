package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Person;

public interface PersonDao {
    boolean create(Person person);

    boolean delete(Person person);

    boolean updatePhoneNumber(Person person, String phone);
}

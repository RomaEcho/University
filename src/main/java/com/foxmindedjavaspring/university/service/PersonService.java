package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Person;

public interface PersonService {

    void addPerson(Person person);

    void removePerson(Person person);

    List<Person> getAllPersons();

}
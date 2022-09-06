package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Person;

public interface PersonService {

    void addPerson(Person person);

    void removePerson(Long id);

    Person getPerson(Long id);

    List<Person> getAllPersons();

}
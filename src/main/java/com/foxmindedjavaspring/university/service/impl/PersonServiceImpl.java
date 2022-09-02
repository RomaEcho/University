package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Person;
import com.foxmindedjavaspring.university.service.PersonService;

@Component
public class PersonServiceImpl implements PersonService {
    private final GenericDao genericDao;

    public PersonServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addPerson(Person person) {
        genericDao.create(person);
    }

    @Override
    public void removePerson(Person person) {
        genericDao.delete(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return genericDao.findAll();
    }
}

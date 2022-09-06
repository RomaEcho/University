package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Person;
import com.foxmindedjavaspring.university.service.PersonService;

@Component
public class PersonServiceImpl implements PersonService {
    private final GenericDao<Person> genericDao;

    public PersonServiceImpl(GenericDao<Person> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addPerson(Person person) {
        genericDao.create(person);
    }

    @Override
    public void removePerson(Long id) {
        genericDao.delete(id);
    }

    @Override
    public Person getPerson(Long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return genericDao.findAll();
    }
}

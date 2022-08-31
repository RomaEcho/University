package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Person;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class PersonServiceImpl implements GenericService<Person> {
    private final GenericDao genericDao;

    public PersonServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(Person person) {
        genericDao.create(person);
    }

    @Override
    public void remove(Person person) {
        genericDao.delete(person);
    }

    @Override
    public List<Person> getAll() {
        return genericDao.findAll();
    }
}

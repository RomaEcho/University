package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Person;

public class PersonServiceImplTest {
    private static final long id = 11;
    private Person person;
    @Mock
    private GenericDao<Person> genericDao;
    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        person = new Person.Builder<>()
                           .withFirstName("firstName")
                           .withLastName("lastName")
                           .withBirthday(LocalDate.of(2017, 1, 13))
                           .withGender("male")
                           .withPhone("222")
                           .withEmail("email")
                           .withAddress("address")
                           .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewPerson() {
        personServiceImpl.addPerson(person);

        verify(genericDao).create(person);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingPerson() {
        personServiceImpl.removePerson(id);

        verify(genericDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingPerson() {
        personServiceImpl.getPerson(id);

        verify(genericDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllPersons() {
        personServiceImpl.getAllPersons();

        verify(genericDao).findAll();
    }
}

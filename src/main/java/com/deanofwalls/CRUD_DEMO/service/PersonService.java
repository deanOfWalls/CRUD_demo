package com.deanofwalls.CRUD_DEMO.service;


import com.deanofwalls.CRUD_DEMO.model.PersonModel;
import com.deanofwalls.CRUD_DEMO.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonModel create(PersonModel person) {
        PersonModel personCreated = repository.save(person);
        return personCreated;
    }

    public PersonModel read(Long id) {
        Optional<PersonModel> potentialPerson = repository.findById(id);
        PersonModel person = potentialPerson.get();
        return person;
    }

    public PersonModel update(Long id, PersonModel person) {
        PersonModel personInDataBase = read(id);
        String newFirstName = person.getFirstName();
        String newLastName = person.getLastName();

        personInDataBase.setFirstName(newFirstName);
        personInDataBase.setLastName(newLastName);
        repository.save(personInDataBase);
        return personInDataBase;
    }

    public PersonModel delete(Long id) {
        PersonModel person = read(id);
        repository.delete(person);
        return person;
    }

    public List<PersonModel> readAll() {
        List<PersonModel> personList = new ArrayList<>();
        repository.findAll().forEach(personList::add);
        return personList;
    }
}
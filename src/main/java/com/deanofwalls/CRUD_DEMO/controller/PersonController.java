package com.deanofwalls.CRUD_DEMO.controller;

import com.deanofwalls.CRUD_DEMO.model.PersonModel;
import com.deanofwalls.CRUD_DEMO.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person-controller")
public class PersonController {
    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonModel> create(@RequestBody PersonModel person) {
        final PersonModel responseBody = service.create(person);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PersonModel> read(@PathVariable Long id) {
        final PersonModel responseBody = service.read(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonModel> update(@PathVariable Long id, @RequestBody PersonModel person) {
        final PersonModel responseBody = service.update(id, person);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PersonModel> delete(@PathVariable Long id) {
        final PersonModel responseBody = service.delete(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<PersonModel>> readAll() {
        final List<PersonModel> responseBody = service.readAll();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}

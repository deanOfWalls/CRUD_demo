package com.deanofwalls.CRUD_DEMO.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;

//import java.util.Date;
import java.time.LocalDate;
import java.util.Date;

//import org.springframework.web.bind.annotation.*;


//designates as persistent class, maps Entity to table with same name of annotated class
//can be rerouted using @Table annotation. Entities are fungible.
//Each Entity MUST be annotated with a respective ID
@Entity
public class PersonModel
{
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private Date birthDate;

    public PersonModel() {
    }

    public PersonModel(Long id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
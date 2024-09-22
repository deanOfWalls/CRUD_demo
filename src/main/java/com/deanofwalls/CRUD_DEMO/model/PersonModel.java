package com.deanofwalls.CRUD_DEMO.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

//import java.util.Date;
import java.time.LocalDate;

//import org.springframework.web.bind.annotation.*;


//designates as persistent class, maps Entity to table with same name of annotated class
//can be rerouted using @Table annotation. Entities are fungible.
//Each Entity MUST be annotated with a respective ID
@Entity
public class PersonModel {
    @Id //Denotes primary key for this Entity (can be auto-generated or generated manually by app)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //annotates Id fields, 'IDENTITY' uses Id column
    private Long id;
    private String productDescription;
    @JsonFormat(pattern = "yyyy-MM-dd") //i added this notation to try to change the format
    private LocalDate timeScanned; //I think the format is: yyyy-MM-ddTHH:mm:ss

    public PersonModel() {

    }

    public PersonModel(Long id, String productDescription, LocalDate timeScanned
    ) {
        this.id = id;
        this.productDescription = productDescription;
        this.timeScanned = timeScanned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public LocalDate getTimeScanned() {
        return timeScanned;
    }

    public void setTimeScanned(LocalDate timeScanned) {
        this.timeScanned = timeScanned;
    }
}

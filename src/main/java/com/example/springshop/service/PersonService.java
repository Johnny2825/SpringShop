package com.example.springshop.service;

import com.example.springshop.entity.Cart;
import com.example.springshop.entity.Person;
import com.example.springshop.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}

package com.example.springshop.service;


import com.example.springshop.entity.Person;
import com.example.springshop.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        System.out.println(person);
        return personRepository.save(person);
    }

    public Person getById(UUID id){
        return personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Нету пользователя"));
    }

    public void delete(Set<Person> set) {
//        personRepository.deleteAllById();
    }
}

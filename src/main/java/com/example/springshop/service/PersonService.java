package com.example.springshop.service;


import com.example.springshop.entity.Person;
import com.example.springshop.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;


@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public Person findByLogin(String login) {
        return personRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден по логину"));
    }

    public Person getById(UUID id){
        return personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Нету пользователя"));
    }

    public void delete(Set<Person> set) {
//        personRepository.deleteAllById();
    }
}

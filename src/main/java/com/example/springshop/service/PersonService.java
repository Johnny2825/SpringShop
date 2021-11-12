package com.example.springshop.service;


import com.example.springshop.entity.Person;
import com.example.springshop.entity.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Person save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public Person findByLogin(String login) {
        return personRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден по логину"));
    }

    public Person findById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден по идентификатору"));
    }

    public void delete(Set<Person> set) {
//        personRepository.deleteAllById();
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void disablePerson(UUID id) {
        var person = findById(id);
        person.setDisabled(true);
        save(person);
    }
}

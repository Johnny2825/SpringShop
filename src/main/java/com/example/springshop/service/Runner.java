package com.example.springshop.service;

import com.example.springshop.entity.Person;
import com.example.springshop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final PersonRepository personRepository;

    public Runner(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setLastName("Kek");
        person.setPhone("89232178");
        person.setPassword("1234");

        person = personRepository.save(person);
        System.out.println(person);
    }
}

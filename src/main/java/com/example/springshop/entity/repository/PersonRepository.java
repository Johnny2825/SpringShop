package com.example.springshop.entity.repository;

import com.example.springshop.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {
    public List<Person> findAll();

    public Optional<Person> findByLogin(String login);
}

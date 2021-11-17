package com.example.springshop.entity.repository;

import com.example.springshop.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findByName(String name);
}

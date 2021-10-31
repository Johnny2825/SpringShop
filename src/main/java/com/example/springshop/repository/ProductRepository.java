package com.example.springshop.repository;

import com.example.springshop.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {

    public List<Product> findAll();
}

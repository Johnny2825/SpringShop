package com.example.springshop.entity.repository;

import com.example.springshop.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> , JpaSpecificationExecutor<Product> {

    public List<Product> findAll();
}

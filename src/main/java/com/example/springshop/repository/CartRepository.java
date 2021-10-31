package com.example.springshop.repository;

import com.example.springshop.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends CrudRepository<Cart, UUID> {

    public Optional<Cart> findByPersonId(UUID id);
}

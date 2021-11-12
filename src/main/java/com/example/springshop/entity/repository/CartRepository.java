package com.example.springshop.entity.repository;

import com.example.springshop.entity.Cart;
import com.example.springshop.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends CrudRepository<Cart, UUID> {
    @Query("select c from Cart c " +
            "where c.owner = :person and c.order is null")
    Optional<Cart> findByCartByPerson(Person person);
}

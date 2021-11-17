package com.example.springshop.service;


import com.example.springshop.entity.Cart;
import com.example.springshop.entity.Person;
import com.example.springshop.entity.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<Cart> findLastCart(Person person) {
        return cartRepository.findByCartByPerson(person);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}

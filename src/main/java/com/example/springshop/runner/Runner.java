package com.example.springshop.runner;

import com.example.springshop.entity.Cart;
import com.example.springshop.entity.repository.CartRepository;
import com.example.springshop.entity.repository.CategoryRepository;
import com.example.springshop.entity.repository.PersonRepository;
import com.example.springshop.entity.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.UUID;

//@Component
public class Runner implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final PersonRepository personRepository;
    private final CartRepository cartRepository;
    private final CategoryRepository categoryRepository;

    public Runner(ProductRepository productRepository, PersonRepository personRepository, CartRepository cartRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.personRepository = personRepository;
        this.cartRepository = cartRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

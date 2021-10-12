package com.example.springshop.runner;

import com.example.springshop.entity.Cart;
import com.example.springshop.entity.Category;
import com.example.springshop.entity.Person;
import com.example.springshop.entity.Product;
import com.example.springshop.repository.CartRepository;
import com.example.springshop.repository.CategoryRepository;
import com.example.springshop.repository.PersonRepository;
import com.example.springshop.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
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
//        Person person = new Person();
//        person.setLastName("Петров");
//        person.setPhone("232178");
//        person.setPassword("1234");
//
//        Cart cart = new Cart();
//        cart.setPerson(person);
//
//        Cart.InnerProduct productIn = new Cart.InnerProduct();
//
//        productIn.setCount(1);
//        productIn.setName("Хлеб");
//        productIn.setPrice(BigDecimal.valueOf(45));
//
//        cart.setProducts(List.of(productIn));
//        personRepository.save(person);
//        cartRepository.save(cart);
//
//        System.out.println(cart.getProducts());
//
//        person = personRepository.save(person);
//        System.out.println(person);


//        Person person = personRepository.findById(UUID.fromString("7d07a3d4-9eb4-48e5-a181-f90c849ddfed")).get();
//        Product product = new Product();
//        product.setPerson(person);
//        product.setName("Сыр");
//        product.setCount(4);
//        product.setPrice(BigDecimal.valueOf(134));
//        product.setCategory(categoryRepository.findById(1L).get());
//        product.setVendorCode("3452");
//
//        Product product1 = new Product();
//        product1.setPerson(person);
//        product1.setName("Молоко");
//        product1.setCount(7);
//        product1.setPrice(BigDecimal.valueOf(65));
//        product1.setCategory(categoryRepository.findById(1L).get());
//        product1.setVendorCode("3455");
//
//        productRepository.saveAll(List.of(product, product1));

    }
}

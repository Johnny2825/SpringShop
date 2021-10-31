package com.example.springshop.service;

import com.example.springshop.entity.Cart;
import com.example.springshop.entity.Person;
import com.example.springshop.entity.Product;
import com.example.springshop.repository.CartRepository;
import com.example.springshop.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    
    private final CartRepository cartRepository;
    private final PersonRepository personRepository;
    private final Person person;
    private Cart cart;

    public CartService(CartRepository cartRepository, PersonRepository personRepository) {
        this.cartRepository = cartRepository;
        this.personRepository = personRepository;
        this.person = this.personRepository.findById(UUID.fromString("7d07a3d4-9eb4-48e5-a181-f90c849ddfed")).get();
    }

    public List<Cart.InnerProduct> getAllProducts() {
        cart = cartRepository.findByPersonId(person.getId()).orElse(new Cart());

        if (cart.getProducts() == null) {
            cart.setPerson(person);
            cart.setProducts(new ArrayList<>());
        }

        return cart.getProducts();
    }

    public void addProductToCart(Set<Product> set) {
        cart.getProducts().addAll(castProductToInnerProduct(set));
        cart = cartRepository.save(cart);
    }

    private List<Cart.InnerProduct> castProductToInnerProduct(Set<Product> setProduct) {
        Set<Cart.InnerProduct> setInnerProduct = new HashSet<>();
        for (Product product : setProduct) {
            Cart.InnerProduct innerProduct = new Cart.InnerProduct();
            innerProduct.setId(product.getId());
            innerProduct.setName(product.getName());
            innerProduct.setPrice(product.getPrice());
            innerProduct.setCount(product.getCount());
            innerProduct.setVendorCode(product.getVendorCode());
            setInnerProduct.add(innerProduct);
        }
        return new ArrayList<>(setInnerProduct);
    }

    public void deleteProductFromCart(Set<Cart.InnerProduct> products) {

    }

}

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

    public CartService(CartRepository cartRepository, PersonRepository personRepository) {
        this.cartRepository = cartRepository;
        this.personRepository = personRepository;
    }

    public List<Cart.InnerProduct> getAllProducts(String id) {
        Cart cart = cartRepository.findById(UUID.fromString(id)).get();
        return cart.getProducts();
    }

    public Cart findCartByPersonId(String id) {
        return cartRepository.findByPersonId(UUID.fromString(id)).orElseThrow(() -> new NoSuchElementException("Такого человека нет"));
    }

    public void addProductToCart(Set<Product> set) {
        Cart cart = new Cart();
        cart.setPerson(personRepository.findById(UUID.fromString("466f83d2-337d-478b-b21f-a3dcd613523e")).orElseThrow(() -> new NoSuchElementException("Нету такого человека")));
        cart.setProducts(castProductToInnerProduct(set));
        cart = cartRepository.save(cart);
        System.out.println(cart);
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

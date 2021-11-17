package com.example.springshop.service;

import com.example.springshop.entity.Product;
import com.example.springshop.entity.filter.ProductFilter;
import com.example.springshop.entity.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllByFilter(ProductFilter productFilter) {
        return productRepository.findAll(productFilter.getSpecification());
    }


}

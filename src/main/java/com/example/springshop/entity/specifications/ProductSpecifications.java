package com.example.springshop.entity.specifications;

import com.example.springshop.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThen(BigDecimal minPrice) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice)
        );
    }

    public static Specification<Product> priceLessOrEqualsThen(BigDecimal maxPrice) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice)
        );
    }

    public static Specification<Product> productNameLike(String name) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), String.format("%%%s%%", name))
        );
    }
}

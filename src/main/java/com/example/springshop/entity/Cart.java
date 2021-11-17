package com.example.springshop.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import elemental.json.Json;
import elemental.json.JsonObject;
import liquibase.pro.packaged.S;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Cart {

    @PrePersist
    public void init() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToOne
    private Order order;

    @Type(type = "jsonb")
    private List<InnerProduct> products;

    public static class InnerProduct implements Serializable {
        private UUID id;
        private String name;
        private long count;
        private BigDecimal price;
        private String vendorCode;

        public UUID getId() {
            return id;
        }

        public InnerProduct setId(UUID id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public InnerProduct setName(String name) {
            this.name = name;
            return this;
        }

        public long getCount() {
            return count;
        }

        public InnerProduct setCount(long count) {
            this.count = count;
            return this;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public InnerProduct setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public String getVendorCode() {
            return vendorCode;
        }

        public InnerProduct setVendorCode(String vendorCode) {
            this.vendorCode = vendorCode;
            return this;
        }
    }

    public Cart addProducts(List<InnerProduct> newProducts) {
        if(this.products == null) {
            this.products = new ArrayList<>();
        }

        for(InnerProduct innerProduct: this.products) {
            for(InnerProduct newProduct: newProducts) {
                if(innerProduct.getId().equals(newProduct.getId())) {
                    innerProduct.setCount(innerProduct.getCount() + 1);
                }
            }
        }

        this.products.addAll(newProducts);

        return this;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<InnerProduct> getProducts() {
        return products;
    }

    public void setProducts(List<InnerProduct> products) {
        this.products = products;
    }
}

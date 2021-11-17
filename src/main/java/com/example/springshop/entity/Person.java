package com.example.springshop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@ToString
@Table(name = "person")
public class Person {

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

    @Column(name = "first_name", length = 128)
    private String firstName;

    @Column(name = "last_name", length = 128, nullable = false)
    private String lastName;

    @Column(name = "patronymic", length = 128)
    private String patronymic;

    @Column(name = "phone", length = 14, nullable = false)
    private String phone;

    @Column(name = "address", length = 1024)
    private String address;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "role", length = 16)
    private String role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "login", length = 128, unique = true)
    private String login;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isDisabled;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Order> listOrders;

//    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    private List<Product> listCreatedProducts;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Cart> listCarts;

    public boolean isDisabled() {
        return isDisabled;
    }

    public Person setDisabled(boolean disabled) {
        isDisabled = disabled;
        return this;
    }

    public Person setId(UUID id) {
        this.id = id;
        return this;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public Person setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Person setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public Person setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Person setRole(String role) {
        this.role = role;
        return this;
    }

    public Person setLogin(String login) {
        this.login = login;
        return this;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }
}

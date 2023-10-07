package com.example.SpringbootEcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;
    @Column(name="card_number")
    private String cardNumber;
//    @OneToMany
//    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderItem> orderItems = new ArrayList<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    private Double total;
//    @OneToMany(mappedBy = "order")
//    private List<CartItem> cartItems = new ArrayList<>();

//    public List<OrderQuantities> getQuantities() {
//        return quantities;
//    }
//    public void setQuantities(List<OrderQuantities> quantities) {
//        this.quantities = quantities;
//    }
//    public Long getId(){return id;}
//    public User getUser(){return user;}
//    public void setUser(User user){this.user=user;}
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
}

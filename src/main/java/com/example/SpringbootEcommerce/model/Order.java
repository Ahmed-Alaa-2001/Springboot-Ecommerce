package com.example.SpringbootEcommerce.model;

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name="address_id")
    private Address address;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderQuantities> quantities = new ArrayList<>();
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

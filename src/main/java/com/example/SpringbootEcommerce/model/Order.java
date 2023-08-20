package com.example.SpringbootEcommerce.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="id",nullable = false)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name="address_id")
    private Address address;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderQuantities> quantities = new ArrayList<>();
    public List<OrderQuantities> getQuantities() {
        return quantities;
    }
    public void setQuantities(List<OrderQuantities> quantities) {
        this.quantities = quantities;
    }
    public long getId(){return id;}
    public void setId(long id){this.id=id;}
    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}


}

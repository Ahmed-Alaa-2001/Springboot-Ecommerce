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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "price",nullable = false)
    private Double price;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();
//    @OneToOne(mappedBy = "product",optional = false,cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonIgnore
//    private CartItem cartItem;
//    @ManyToOne(optional = false,cascade = CascadeType.ALL)
//    @JsonIgnore
//    private CartItem cartItem;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<OrderQuantities> orderQuantities=new ArrayList<>();


//    public List<OrderQuantities> getOrderQuantities() {
//        return orderQuantities;
//    }

//    public void setOrderQuantities(List<OrderQuantities> orderQuantities) {
//        this.orderQuantities = orderQuantities;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public CartItem getCartItem() {
//        return cartItem;
//    }
//
//    public void setCartItem(CartItem cartItem) {
//        this.cartItem = cartItem;
//    }
}

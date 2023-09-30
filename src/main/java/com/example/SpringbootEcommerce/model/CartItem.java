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
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    private Product product;

//    @JoinColumn(name = "product_name", referencedColumnName = "name", unique = false)
//    @JsonIgnore
//    @OneToOne(optional = false,orphanRemoval = true,cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "product_id",nullable = false, unique = false)
//    private Product product;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "cart_item_id", nullable = false)
//    private List<Product> products = new ArrayList<>();
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }

}

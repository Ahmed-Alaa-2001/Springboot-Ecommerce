package com.example.SpringbootEcommerce.model;

import jakarta.persistence.*;
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "product_id",nullable = false,unique = true)
    private Product product;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

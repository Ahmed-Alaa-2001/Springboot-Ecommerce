package com.example.SpringbootEcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

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
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

//    @JoinColumn(name = "product_name", referencedColumnName = "name", unique = false)
//    @JsonIgnore
//    @OneToOne(optional = false,orphanRemoval = true,cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "product_id",nullable = false, unique = false)
//    private Product product;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "cart_item_id", nullable = false)
//    private List<Product> products = new ArrayList<>();

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

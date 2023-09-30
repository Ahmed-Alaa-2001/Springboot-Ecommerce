package com.example.SpringbootEcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "cart")
    private List<CartItem>cartItems=new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private User user;
    @Column(name = "total")
    private Double totalPrice;

//    public Long getId() {
//        return id;
//    }
//
//    public List<CartItem> getCartItems() {
//        return cartItems;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setCartItems(List<CartItem> cartItems) {
//        this.cartItems = cartItems;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

}

package com.example.SpringbootEcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="username",nullable = false,unique = true)
    private String userName;
    @JsonIgnore
    @Column(name="password",nullable = false)
    //@Size(min=3, max=255)
 //   @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="first_name",nullable = false)
    private String firstName;
    @Column(name="last_name",nullable = false)
    private String lastName;
    @Pattern(regexp = "[0-9]{16,18}", message = "Invalid card number")
    private String creditCard;
//    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Address address;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Cart cart;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "order_id", unique = true)
    private Order order;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Order> orders = new ArrayList<>();

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    public List<Address>getAddressList(){
//        return addressList;
//    }
//    public String getEmail(){return email;}
//    public void setEmail(String email){this.email=email;}
//    public String getFirstName(){return firstName;}
//    public void setFirstName(String firstName){this.firstName=firstName;}
//    public String getLastName(){return lastName;}
//    public void setLastName(String lastName){this.lastName=lastName;}
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword(){return password;}
//    public void setPassword(String password){this.password=password;}
//    public Long getId(){return id;}
//    public void setId(Long id){this.id=id;}
//
//    public void setAddressList(List<Address> addressList) {
//        this.addressList = addressList;
//    }
}

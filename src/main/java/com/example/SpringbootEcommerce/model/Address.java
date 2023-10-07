package com.example.SpringbootEcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="address",nullable = false)
    private String address;
    @Column(name="city",nullable = false)
    private String city;
    @Column(name="country",nullable = false)
    private String country;
    @JsonIgnore
    @OneToOne(optional = false)
    @JoinColumn(name="user_id",nullable = false)
    private User user;
//    public Long getId(){return id;}
//    public String getAddress(){return address;}
//    public void setAddress(String address){this.address=address;}
//    public String getCity(){return city;}
//    public void setCity(String city){this.city=city;}
//    public String getCountry(){return country;}
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setCountry(String country){this.country=country;}

}

package com.example.SpringbootEcommerce.model;

import jakarta.persistence.*;

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
    @ManyToOne(optional = false)
    @JoinColumn(name="user-id",nullable = false)
    private User user;
    public long getId(){return id;}
    public void setId(long id){this.id=id;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}
    public String getCity(){return city;}
    public void setCity(String city){this.city=city;}
    public String getCountry(){return country;}
    public void setCountry(String country){this.country=country;}
}

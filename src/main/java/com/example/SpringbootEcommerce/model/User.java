package com.example.SpringbootEcommerce.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="username",nullable = false,unique = true)
    private String username;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="first_name",nullable = false)
    private String firstName;
    @Column(name="last_name",nullable = false)
    private String lastName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addressList=new ArrayList<>();

    public List<Address>getAddressList(){
        return addressList;
    }
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName){this.firstName=firstName;}
    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public String getUserName(){return username;}
    public void setUserName(String username){this.username=username;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
    public long getId(){return id;}
    public void setId(long id){this.id=id;}

}

package com.example.SpringbootEcommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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
    private String userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
    public long getId(){return id;}
    public void setId(long id){this.id=id;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}

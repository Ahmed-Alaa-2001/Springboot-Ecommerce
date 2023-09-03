package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.User;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserNameIgnoreCase(String userName);


    Optional<User> findByEmailIgnoreCase(String email);
}

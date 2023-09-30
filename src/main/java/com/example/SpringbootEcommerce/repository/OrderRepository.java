package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.Order;
import com.example.SpringbootEcommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);

}

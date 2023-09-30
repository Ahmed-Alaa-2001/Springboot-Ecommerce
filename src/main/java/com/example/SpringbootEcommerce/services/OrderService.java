package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.model.Order;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public List<Order> findOrder(User user){
        return orderRepository.findByUser(user);
    }
}


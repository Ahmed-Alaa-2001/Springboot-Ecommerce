package com.example.SpringbootEcommerce.controller;

import com.example.SpringbootEcommerce.model.Order;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/getorder")
    private List<Order> getOrder(@AuthenticationPrincipal User user){
        return orderService.findOrder(user);
    }
}

package com.example.SpringbootEcommerce.controller;

import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.exceptions.WrongCreditCart;
import com.example.SpringbootEcommerce.model.Order;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/getorder")
    private ResponseEntity getOrder(@AuthenticationPrincipal User user){
//        return orderService.findOrder(user);
        return new ResponseEntity<List<Order>>(orderService.findOrder(user), HttpStatus.FOUND);
    }
    @PostMapping("/addorder")
    private ResponseEntity addOrder(@RequestBody String creditCard, @AuthenticationPrincipal User user){
        try{
            return new ResponseEntity<Order>(orderService.addOrder(creditCard,user), HttpStatus.CREATED);
        } catch (WrongCreditCart err) {
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (ProductNotFound err) {
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

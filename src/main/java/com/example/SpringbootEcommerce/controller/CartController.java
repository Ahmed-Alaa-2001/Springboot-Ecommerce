package com.example.SpringbootEcommerce.controller;

import com.example.SpringbootEcommerce.dto.UserDTO;
import com.example.SpringbootEcommerce.exceptions.CartNotFound;
import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.exceptions.UserNotFound;
import com.example.SpringbootEcommerce.model.Cart;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity addItemToUserCart(@RequestBody String productName, @AuthenticationPrincipal User user){
        try{

            return new ResponseEntity<Cart>(cartService.addItemsToCart(productName,user), HttpStatus.CREATED);
        }catch (UserNotFound err){
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NOT_FOUND);
        }catch (ProductNotFound err){
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/usercart")
    public ResponseEntity getUserCart(@AuthenticationPrincipal User user){
        try{
            return new ResponseEntity<Cart>(cartService.getUserCart(user), HttpStatus.CREATED);
        }catch (CartNotFound err){
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

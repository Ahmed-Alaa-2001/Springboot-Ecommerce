package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.model.CartItem;
import com.example.SpringbootEcommerce.repository.CartItemRepository;
import com.example.SpringbootEcommerce.model.Product;
import com.example.SpringbootEcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

//    public CartItem addProductToCartItem(String productName) throws ProductNotFound {
//        System.out.println(productName);
//        Optional<Product>opProduct = productRepository.findByNameIgnoreCase(productName);
//        if(!opProduct.isPresent()){
//            throw new ProductNotFound("There is no product with given name");
//        }
//        Product product = opProduct.get();
//        CartItem cartItem = new CartItem();
//        System.out.println("hhhhhhhhhhhhhhhhh");
//        cartItem.setProduct(product);
//        System.out.println("siiiiiiiiiiiiiiiiiiiu");
//        cartItem.setQuantity(1);
//        System.out.println(cartItem.getProduct().getName());
//        return cartItem;
//    }
    public CartItem addProductToCartItem(String productName) throws ProductNotFound {
        System.out.println(productName);
        Optional<Product>opProduct = productRepository.findByNameIgnoreCase(productName);
        if(!opProduct.isPresent()){
            throw new ProductNotFound("There is no product with given name");
        }
        Product product = opProduct.get();
        CartItem cartItem = new CartItem();
        System.out.println("hhhhhhhhhhhhhhhhh");
        cartItem.setProduct(product);
        System.out.println("siiiiiiiiiiiiiiiiiiiu");
        cartItem.setQuantity(1);


        System.out.println(cartItem.getProduct().getName());
        return cartItem;
    }
}

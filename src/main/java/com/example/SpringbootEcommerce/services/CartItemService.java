package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.model.*;
import com.example.SpringbootEcommerce.repository.CartItemRepository;
import com.example.SpringbootEcommerce.repository.CartRepository;
import com.example.SpringbootEcommerce.repository.OrderRepository;
import com.example.SpringbootEcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

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
//    public CartItem addProductToCartItem(String productName,User user) throws ProductNotFound {
//        System.out.println(productName);
//        Optional<Product>opProduct = productRepository.findByNameIgnoreCase(productName);
//        if(!opProduct.isPresent()){
//            throw new ProductNotFound("There is no product with given name");
//        }
//        Product product = opProduct.get();
//        CartItem cartItem = new CartItem();
//        cartItem.setProduct(product);
//        cartItem.setQuantity(1);
////        List<Order> opOrder = orderRepository.findByUser(user);
//
////        System.out.println(cartItem.getOrder());
//        System.out.println(cartItem.getProduct().getId());
//        return cartItem;
//    }
    public CartItem addProductToCartItem(String productName,User user) throws ProductNotFound {
        System.out.println(productName);
        Optional<Product>opProduct = productRepository.findByNameIgnoreCase(productName);
        if(!opProduct.isPresent()){
            throw new ProductNotFound("There is no product with given name");
        }
        Product product = opProduct.get();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
//        List<Order> opOrder = orderRepository.findByUser(user);

//        System.out.println(cartItem.getOrder());
        System.out.println(cartItem.getProduct().getId());
        return cartItem;
    }
    public Cart DeleteCartItem(User user) throws ProductNotFound {
        if(user.getCart()==null||user.getCart().getCartItems().isEmpty()){
            throw new ProductNotFound("There is no product in this cart");
        }
        System.out.println("77777777777777777");
//        cartRepository.delete(user.getCart());
//        for(CartItem cartItem:user.getCart().getCartItems()){
//            cartItem.de
//        }
        List<CartItem> cartItems = user.getCart().getCartItems();
//        List<CartItem> cartItems =
        System.out.println(cartItems);
        System.out.println("888888888888888888");
        for(CartItem cartItem : cartItems){
            cartItemRepository.deleteById(cartItem.getId());
        }
        user.getCart().setTotalPrice(0D);
        cartRepository.save(user.getCart());
        return user.getCart();
    }
}





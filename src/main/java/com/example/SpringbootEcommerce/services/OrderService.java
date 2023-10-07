package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.exceptions.WrongCreditCart;
import com.example.SpringbootEcommerce.model.*;
import com.example.SpringbootEcommerce.repository.CartItemRepository;
import com.example.SpringbootEcommerce.repository.CartRepository;
import com.example.SpringbootEcommerce.repository.OrderItemRepository;
import com.example.SpringbootEcommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    OrderItemRepository orderItemRepository;

    public List<Order> findOrder(User user) {
        System.out.println(user);
        return orderRepository.findByUser(user);
    }

    public Order addOrder(String creditCard, User user) throws ProductNotFound, WrongCreditCart {
//        Order order = new Order();
        Order order = user.getOrder();
//        List<CartItem> cartItem = user.getCart().getCartItems();
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new ProductNotFound("There is no product in this cart");
        }
        if (!creditCard.equals(user.getCreditCard())) {
            throw new WrongCreditCart("You entered wrong credit cart");
        }
//        System.out.println(order.getTotal());
        if (user.getOrder().getTotal() == null) {
            System.out.println(order.getTotal());
            order.setTotal(user.getCart().getTotalPrice());
            System.out.println(order.getTotal());
        } else {
            System.out.println(order.getTotal() + user.getCart().getTotalPrice());
            order.setTotal(order.getTotal() + user.getCart().getTotalPrice());
        }
//        order.setUser(user);
        order.setAddress(user.getAddress());
        order.setCardNumber(creditCard);
        Order order1 = orderRepository.save(order);
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrderItemPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
            orderItem.setOrder(order1);
            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);
        cartItemService.DeleteCartItem(user);
//        cartItems.clear();
        return order1;
    }


//    @Transactional
//    public Order addOrder(String creditCard, User user) throws ProductNotFound, WrongCreditCart {
//        Order order = new Order();
//        System.out.println("0000000000000000");
////        List<CartItem> cartItem = user.getCart().getCartItems();
//        Cart cart = user.getCart();
//        List<CartItem> cartItems = cart.getCartItems();
//        if (cartItems.isEmpty()) {
//            throw new ProductNotFound("There is no product in this cart");
//        }
//        if (!creditCard.equals(user.getCreditCard())) {
//            throw new WrongCreditCart("You entered wrong credit cart");
//        } else {
//            if (order.getTotal() == null) {
//                order.setTotal(user.getCart().getTotalPrice());
//            } else {
//                order.setTotal(order.getTotal() + user.getCart().getTotalPrice());
//            }
//            order.setUser(user);
//            order.setAddress(user.getAddress());
//            order.setCardNumber(creditCard);
//            System.out.println(cart);
//            System.out.println(order.getCartItems());
//            List<CartItem> orderCartItems = new ArrayList<>();
//            for (CartItem cartItem : cartItems) {
//                CartItem newCartItem = new CartItem();
//                newCartItem.setCart(user.getCart());
//                newCartItem.setId(cartItem.getId());
//                newCartItem.setProduct(cartItem.getProduct());
//                newCartItem.setQuantity(cartItem.getQuantity());
//                newCartItem.setOrder(order);
//                orderCartItems.add(newCartItem);
//                cartItemRepository.save(newCartItem);
//            }
//        order = orderRepository.save(order);
//        cartItemRepository.saveAll(orderCartItems);
////            cart.getCartItems().clear();
////            for (CartItem cartItem1 : cart.getCartItems()) {
////                cartItemRepository.delete(cartItem1);
////            }
////            cartRepository.save(cart);
//            System.out.println("777777777777777");
//        }
////        System.out.println(order.getCartItems().get(0).getProduct().getName());
////        Order order1=orderRepository.findById(5L).get();
////        System.out.println(order1.getCartItems().get(0).getProduct().getName());
//        return order;
}












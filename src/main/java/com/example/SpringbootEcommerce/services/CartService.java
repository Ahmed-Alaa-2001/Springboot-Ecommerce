package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.dto.UserDTO;
import com.example.SpringbootEcommerce.exceptions.CartNotFound;
import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.exceptions.UserNotFound;
import com.example.SpringbootEcommerce.model.Cart;
import com.example.SpringbootEcommerce.model.CartItem;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.repository.CartItemRepository;
import com.example.SpringbootEcommerce.repository.CartRepository;
import com.example.SpringbootEcommerce.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart addItemsToCart(String productName, User currentUser) throws UserNotFound, ProductNotFound {
        System.out.println(currentUser.getUserName());
        Optional<User>opUser = userRepository.findByUserNameIgnoreCase(currentUser.getUserName());
        if(!opUser.isPresent()){
            throw new UserNotFound("User doesn't exist");
        }
        CartItem item = cartItemService.addProductToCartItem(productName,currentUser);
//        System.out.println(item.getProduct().getName());
        User user = opUser.get();
        Cart userCart = user.getCart();
        item.setCart(userCart);
        //item.setProduct(productRepository.findByNameIgnoreCase(productName).orElseThrow(() -> new ProductNotFound("Product not found")));
//        if (userCart == null) {
//            userCart = new Cart();
//            System.out.println(userCart);
//            userCart.setUser(user);
//            user.setCart(userCart);
//        }
//        List<CartItem> cartItems = Optional.ofNullable(userCart.getCartItems()).orElse(Collections.emptyList());
        List<CartItem> cartItems = userCart.getCartItems();
        if(cartItems.isEmpty()){
            cartItems.add(item);
            System.out.println(cartItems.get(0).getId());
            userCart.setTotalPrice(item.getProduct().getPrice());
            System.out.println(userCart.getTotalPrice());
        }
        else{
            boolean productExists = false;
            for (CartItem c:cartItems){
                if(c.getProduct().getName().equals(productName)){
                    productExists = true;
                    c.setQuantity(c.getQuantity()+1);
                    userCart.setTotalPrice(userCart.getTotalPrice()+c.getProduct().getPrice());
                    break;
                }
            }
            if(!productExists){
                cartItems.add(item);
                userCart.setTotalPrice(userCart.getTotalPrice()+item.getProduct().getPrice());
            }
        }
        return cartRepository.save(user.getCart());
    }
    public Cart getUserCart(User user) throws CartNotFound {
        Cart cart=user.getCart();
        Optional<Cart>opCart = cartRepository.findById(cart.getId());
        if(!opCart.isPresent()){
            throw new CartNotFound("User's cart is empty");
        }
        System.out.println(opCart.get());
        return opCart.get();
    }
    public CartItem deleteItemUserCart(User user,String ProductName) throws ProductNotFound {
        Cart cart=user.getCart();
        Optional<CartItem>opCartItem = cartItemRepository.findCartItemsByProductNameAndUsername(ProductName,user.getUserName());
//        System.out.println(ProductName+" "+user.getUserName());
        if(!opCartItem.isPresent()){
            throw new ProductNotFound("Product isn't exist in this user cart");
        }
        CartItem cartItem= opCartItem.get();
        cartItem.getCart().setTotalPrice(cartItem.getCart().getTotalPrice()-cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItemRepository.deleteById(opCartItem.get().getId());
//        System.out.println(user.getCart());
        return opCartItem.get();
    }






//    @Transactional
//    public CartItem deleteItemUserCart(User user,String ProductName) throws ProductNotFound {
//        Cart cart=user.getCart();
//        Optional<CartItem>opCartItem = cartItemRepository.findCartItemsByProductNameAndUsername(ProductName,user.getUserName());
//        System.out.println(ProductName+" "+user.getUserName());
//        System.out.println(opCartItem.get());
//        if(!opCartItem.isPresent()){
//            throw new ProductNotFound("Product isn't exist in this user cart");
//        }
//        cartItemRepository.deleteById(opCartItem.get().getId());
////        System.out.println(user.getCart());
//        return opCartItem.get();
//    }

//public Cart addItemsToCart(String productName, User currentUser) throws UserNotFound, ProductNotFound {
//    Optional<User>opUser = userRepository.findByUserNameIgnoreCase(currentUser.getUserName());
//    if(!opUser.isPresent()){
//        throw new UserNotFound("User doesn't exist");
//    }
//    CartItem item = cartItemService.addProductToCartItem(productName);
//    User user = opUser.get();
//    Cart userCart = user.getCart();
//    item.setCart(userCart);
//    List<CartItem> cartItems = userCart.getCartItems();
//    if(cartItems.isEmpty()){
//        cartItems.add(item);
//        System.out.println(cartItems.get(0).getProduct().getName()+"hh");
//        userCart.setTotalPrice(item.getProduct().getPrice());
//        System.out.println(userCart.getTotalPrice());
//    }
//    else{
//        boolean productExists = false;
//        for (CartItem c:cartItems){
//            if(c.getProduct().getName().equals(productName)){
//                productExists = true;
//                c.setQuantity(c.getQuantity()+1);
//                userCart.setTotalPrice(userCart.getTotalPrice()+c.getProduct().getPrice());
//                break;
//            }
//        }
//        if(!productExists){
//            cartItems.add(item);
//            userCart.setTotalPrice(userCart.getTotalPrice()+item.getProduct().getPrice());
//        }
//    }
//    System.out.println("3333333333333");
//    System.out.println(user.getCart());
//    System.out.println(userCart);
//    user.getCart().getCartItems().get(0).getProduct().setName(user.getCart().getCartItems().get(0).getProduct().getName()+user.getUserName());
//    System.out.println(user.getCart().getCartItems().get(0).getProduct().getName());
//    cartItemRepository.customSave(productName,user.getCart().getId());
//    return cartRepository.save(user.getCart());
////    Optional<Cart>opcar = cartRepository.findById(1L);
////    System.out.println(opcar.get().getCartItems().get(0).getProduct().getName());
////    return car;
//}
}

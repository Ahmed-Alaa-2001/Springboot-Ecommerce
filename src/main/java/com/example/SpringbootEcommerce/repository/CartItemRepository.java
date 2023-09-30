package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO cart_item (product_name, cart_id, quantity) VALUES (:productName, :cartId, :quantity)", nativeQuery = true)
//    void customSave(@Param("productName") String productName, @Param("cartId") Long cartId, @Param("quantity") Integer quantity);
//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO cart_item (product_name,quantity) VALUES (:productName,:quantity)", nativeQuery = true)
//    void customSave(@Param("productName") String productName,@Param("quantity") Integer quantity);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cart_item (product_name) VALUES (:productName) WHERE cart_id = :cartId", nativeQuery = true)
    void customSave(@Param("productName") String productName, @Param("cartId") Long cartId);
}
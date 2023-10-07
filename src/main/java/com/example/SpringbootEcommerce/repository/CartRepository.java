package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Override
    <S extends Cart> S save(S entity);

    @Query("delete from Cart c where c.id = ?1")
    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Override
    void deleteById(Long aLong);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cart_item (product_name, cart_id, quantity) VALUES (:productName, :cartId, :quantity)", nativeQuery = true)
    void customSave(@Param("productName") String productName, @Param("cartId") Long cartId, @Param("quantity") Integer quantity);

}

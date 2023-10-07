package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.CartItem;
import com.example.SpringbootEcommerce.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

//    @Transactional
//    @Modifying
////    @Query("delete from CartItem c where upper(c.product.name) = upper(?1)")
////    void deleteByProduct_NameIgnoreCase(String name);
//    @Query("DELETE FROM CartItem c WHERE UPPER(c.product.name) = UPPER(:name)")
//    void deleteByProduct_NameIgnoreCase(@Param("name") String name);

    @Transactional
    @Query("SELECT ci FROM CartItem ci " +
            "JOIN ci.cart c " +
            "JOIN ci.product p " +
            "JOIN c.user u " +
            "WHERE UPPER(p.name) = UPPER(:productName) AND u.userName = :username")
    Optional<CartItem> findCartItemsByProductNameAndUsername(
            @Param("productName") String productName,
            @Param("username") String username
    );

    @Query("select c from CartItem c where c.cart.user = ?1")
    List<CartItem> findByCart_User(User user);


    @Query("delete from CartItem c where c.id = ?1")
    @Transactional
    @Modifying
    @Override
    void deleteById(Long aLong);
}
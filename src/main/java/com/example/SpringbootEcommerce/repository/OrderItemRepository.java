package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}

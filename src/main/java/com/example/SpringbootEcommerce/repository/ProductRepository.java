package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    Optional<Product> findByNameIgnoreCase(String name);

    @Query("select p from Product p where p.id = ?1")
    @Override
    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);
}

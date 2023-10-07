package com.example.SpringbootEcommerce.repository;

import com.example.SpringbootEcommerce.model.Address;
import com.example.SpringbootEcommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a where a.id = ?1 AND a.user.userName =?2")
    Optional<Address> findByAddressAndUserName(Long id,String username);

    @Query("delete from Address a where a.id = ?1")
    @Transactional
    @Modifying
    @Override
    void deleteById(Long aLong);
}

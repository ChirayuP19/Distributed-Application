package com.chirayu.repository;

import com.chirayu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
    @Query("SELECT p.price FROM Product p WHERE p.id = :id")
    BigDecimal findProductPriceByProductId(@Param("id") Long id);
}

package com.ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecom.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByName(String name);
    boolean existsByProductId(Long  productId);
    Product findByProductId(Long productId);
}

package com.be.electroniccomponentstore.repository;

import com.be.electroniccomponentstore.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}

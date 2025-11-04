package com.e_commerce_system.backend.repository;

import com.e_commerce_system.backend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

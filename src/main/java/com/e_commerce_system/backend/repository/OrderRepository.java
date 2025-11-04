package com.e_commerce_system.backend.repository;

import com.e_commerce_system.backend.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

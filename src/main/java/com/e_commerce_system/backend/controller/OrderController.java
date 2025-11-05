package com.e_commerce_system.backend.controller;

import com.e_commerce_system.backend.domain.Order;
import com.e_commerce_system.backend.dto.OrderRequest;
import com.e_commerce_system.backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(
                orderRequest.getMemberId(),
                orderRequest.getProductId(),
                orderRequest.getQuantity()
        );
        return ResponseEntity.ok(order);
    }
}

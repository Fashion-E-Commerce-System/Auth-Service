//package com.e_commerce_system.backend.service;
//
//import com.e_commerce_system.backend.repository.MemberRepository;
//import com.e_commerce_system.backend.repository.ProductRepository;
//import com.e_commerce_system.backend.repository.OrderRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final MemberRepository memberRepository;
//    private final ProductRepository productRepository;
//
//    @Transactional
//    public Order createOrder(Long memberId, Long productId, Integer quantity) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        product.decreaseStock(quantity);
//
//        productRepository.save(product);
//
//        Order order = new Order(null, member, product, quantity);
//
//        return orderRepository.save(order);
//    }
//}

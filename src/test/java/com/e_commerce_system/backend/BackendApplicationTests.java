//package com.e_commerce_system.backend;
//
//
//
//import com.e_commerce_system.backend.dto.OrderRequest;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class BackendApplicationTests {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    private Product product;
//    private Member member;
//
//    @BeforeEach
//    void setUp() {
//        orderRepository.deleteAll();
//        productRepository.deleteAll();
//        memberRepository.deleteAll();
//
//        product = new Product(null, "Test Product", 100, "Test Description", 10);
//        product = productRepository.save(product);
//
//        member = new Member(null, "testuser", "password","email");
//        member = memberRepository.save(member);
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void shouldGetAllProducts() {
//        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/products", Product[].class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).hasSize(1);
//    }
//
//    @Test
//    void shouldGetProductById() {
//        ResponseEntity<Product> response = restTemplate.getForEntity("http://localhost:" + port + "/api/products/" + product.getId(), Product.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().getId()).isEqualTo(product.getId());
//        assertThat(response.getBody().getName()).isEqualTo("Test Product");
//    }
//
//    @Test
//    void shouldCreateOrder() {
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setMemberId(member.getId());
//        orderRequest.setProductId(product.getId());
//        orderRequest.setQuantity(5);
//
//        ResponseEntity<Order> response = restTemplate.postForEntity("http://localhost:" + port + "/api/orders", orderRequest, Order.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().getId()).isNotNull();
//        assertThat(response.getBody().getMember().getId()).isEqualTo(member.getId());
//        assertThat(response.getBody().getProduct().getId()).isEqualTo(product.getId());
//        assertThat(response.getBody().getQuantity()).isEqualTo(5);
//
//        Product updatedProduct = productRepository.findById(product.getId()).get();
//        assertThat(updatedProduct.getStock()).isEqualTo(5);
//    }
//}

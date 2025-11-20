package com.e_commerce_system.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "shopping_cart_items", uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "article_id"}))
@Getter
@Setter
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false)
    private Article article;

    @Column(nullable = false)
    private Integer quantity;
}

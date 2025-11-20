package com.e_commerce_system.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "stock")
@Getter
@Setter
public class Stock {

    @Id
    @Column(name = "article_id")
    private Long articleId; // This is also a foreign key

    @OneToOne
    @MapsId // This maps the primary key of Stock to the primary key of Article
    @JoinColumn(name = "article_id", referencedColumnName = "article_id")
    private Article article;

    @Column(nullable = false)
    private Integer quantity;
}

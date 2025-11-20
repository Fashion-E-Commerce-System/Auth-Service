package com.e_commerce_system.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_history")
@Getter
@Setter
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseId;

    @Column(name = "t_dat")
    private LocalDate tDat;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id")
    private Article article;

    @Column(precision = 10, scale = 6)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "sales_channel_id", referencedColumnName = "sales_channel_id")
    private SalesChannel salesChannel;
}

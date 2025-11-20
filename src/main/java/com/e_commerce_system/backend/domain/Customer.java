package com.e_commerce_system.backend.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @Column(name = "customer_id")
    private String customerId;

    private String fn;
    private String active;
    private Integer age;

    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private ClubMemberStatus clubMemberStatus;

    @ManyToOne
    @JoinColumn(name = "freq_id", referencedColumnName = "freq_id")
    private FashionNewsFrequency fashionNewsFrequency;
}

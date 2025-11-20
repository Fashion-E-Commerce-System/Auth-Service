package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "product_types")
@Getter
@Setter
public class ProductType {

    @Id
    @Column(name = "product_type_no")
    private Integer productTypeNo;

    @Column(name = "product_type_name")
    private String productTypeName;
}

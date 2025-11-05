package com.e_commerce_system.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.e_commerce_system.backend.exception.InsufficientStockException;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String description;

    private Integer stock;

    public void decreaseStock(int quantity) {
        if (this.stock < quantity) {
            throw new InsufficientStockException(this.id, quantity, this.stock);
        }
        this.stock -= quantity;
    }

}

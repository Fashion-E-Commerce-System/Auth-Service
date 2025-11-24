package com.e_commerce_system.backend.domain.product;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "graphical_appearances")
@Getter
@Setter
public class GraphicalAppearance {

    @Id
    private Integer graphicalAppearanceNo;

    private String graphicalAppearanceName;


}


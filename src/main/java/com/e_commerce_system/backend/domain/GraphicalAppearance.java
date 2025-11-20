package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
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
    @Column(name = "graphical_appearance_no")
    private Integer graphicalAppearanceNo;

    @Column(name = "graphical_appearance_name")
    private String graphicalAppearanceName;
}

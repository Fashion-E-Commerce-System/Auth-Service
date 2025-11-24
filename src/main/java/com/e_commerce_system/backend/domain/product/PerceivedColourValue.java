package com.e_commerce_system.backend.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "perceived_colour_values")
@Getter
@Setter
public class PerceivedColourValue {

    @Id
    private Integer perceivedColourValueId;

    private String perceivedColourValueName;
}

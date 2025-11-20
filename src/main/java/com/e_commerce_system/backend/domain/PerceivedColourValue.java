package com.e_commerce_system.backend.domain;

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
    @Column(name = "perceived_colour_value_id")
    private Integer perceivedColourValueId;

    @Column(name = "perceived_colour_value_name")
    private String perceivedColourValueName;
}

package com.e_commerce_system.backend.domain.product;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "colour_groups")
@Getter
@Setter
public class ColourGroup {

    @Id
    private Integer colourGroupCode;

    private String colourGroupName;


}

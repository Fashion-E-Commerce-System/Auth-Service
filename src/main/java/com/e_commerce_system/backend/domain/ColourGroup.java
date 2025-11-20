package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
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
    @Column(name = "colour_group_code")
    private Integer colourGroupCode;

    @Column(name = "colour_group_name")
    private String colourGroupName;
}

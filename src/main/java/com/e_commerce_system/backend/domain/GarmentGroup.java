package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "garment_groups")
@Getter
@Setter
public class GarmentGroup {

    @Id
    @Column(name = "garment_group_no")
    private Integer garmentGroupNo;

    @Column(name = "garment_group_name")
    private String garmentGroupName;
}

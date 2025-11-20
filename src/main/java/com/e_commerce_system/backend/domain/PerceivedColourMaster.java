package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "perceived_colour_masters")
@Getter
@Setter
public class PerceivedColourMaster {

    @Id
    @Column(name = "perceived_colour_master_id")
    private Integer perceivedColourMasterId;

    @Column(name = "perceived_colour_master_name")
    private String perceivedColourMasterName;
}

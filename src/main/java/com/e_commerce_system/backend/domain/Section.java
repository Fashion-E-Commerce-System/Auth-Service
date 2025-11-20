package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "sections")
@Getter
@Setter
public class Section {

    @Id
    @Column(name = "section_no")
    private Integer sectionNo;

    @Column(name = "section_name")
    private String sectionName;
}

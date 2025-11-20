package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {

    @Id
    @Column(name = "department_no")
    private Integer departmentNo;

    @Column(name = "department_name")
    private String departmentName;
}

package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "index_groups")
@Getter
@Setter
public class IndexGroup {

    @Id
    @Column(name = "index_code", length = 10)
    private String indexCode;

    @Column(name = "index_name")
    private String indexName;

    @Column(name = "index_group_no")
    private Integer indexGroupNo;

    @Column(name = "index_group_name")
    private String indexGroupName;
}

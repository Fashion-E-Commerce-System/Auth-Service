package com.e_commerce_system.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "club_member_status")
@Getter
@Setter
public class ClubMemberStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_name", unique = true, length = 50)
    private String statusName;
}

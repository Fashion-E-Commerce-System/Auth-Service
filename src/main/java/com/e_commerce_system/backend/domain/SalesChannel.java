package com.e_commerce_system.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_channels")
@Getter
@Setter
public class SalesChannel {

    @Id
    @Column(name = "sales_channel_id")
    private Integer salesChannelId;

    @Column(name = "channel_name", nullable = false, length = 50)
    private String channelName;
}

package com.e_commerce_system.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {
    private String name;
    private String email;
    private String password;
}

package com.e_commerce_system.backend.controller;


import com.e_commerce_system.backend.domain.Member;
import com.e_commerce_system.backend.service.MemberService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<Member> me() {
        return ResponseEntity.ok().build();
    }
}

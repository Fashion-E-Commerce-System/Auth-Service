package com.e_commerce_system.backend.controller;


import com.e_commerce_system.backend.domain.Member;
import com.e_commerce_system.backend.dto.MemberRequest;
import com.e_commerce_system.backend.service.MemberService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;
    @PostMapping
    public ResponseEntity<Member> signUp(@RequestBody MemberRequest memberRequest) {
        Member member=memberService.signUp(memberRequest.getName(),memberRequest.getEmail(),memberRequest.getPassword());
        return ResponseEntity.ok(member);
    }
}

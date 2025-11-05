package com.e_commerce_system.backend.service;


import com.e_commerce_system.backend.domain.Member;

import com.e_commerce_system.backend.repository.MemberRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemberService {
    private MemberRepository memberRepository;
    public Member signUp(String username, String password, String email) {
        Member member=new Member(null,username,password,email);
        return memberRepository.save(member);
    }
}

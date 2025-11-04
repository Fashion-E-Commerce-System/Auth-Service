package com.e_commerce_system.backend.repository;

import com.e_commerce_system.backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

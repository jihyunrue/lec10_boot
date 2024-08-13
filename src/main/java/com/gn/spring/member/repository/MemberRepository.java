package com.gn.spring.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gn.spring.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{

	Member findBymemNo(Long board_no);
	
	Member findBymemId(String mem_id);
}
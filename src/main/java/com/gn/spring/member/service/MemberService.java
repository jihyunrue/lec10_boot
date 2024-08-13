package com.gn.spring.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gn.spring.member.domain.Member;
import com.gn.spring.member.domain.MemberDto;
import com.gn.spring.member.repository.MemberRepository;


@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public MemberService(PasswordEncoder passwordEncoder, 
			MemberRepository memberRepository) {
		this.passwordEncoder = passwordEncoder;
		this.memberRepository = memberRepository;
	}
	public int createMember(MemberDto dto) {
		// mem_id, mem_pw, mem_name, mem_auth
		int result = -1;
		try {
			dto.setMem_pw(passwordEncoder.encode(dto.getMem_pw()));
			Member member = dto.toEntity();
			// save함수를 사용하면 결과가 entity값으로 리턴 
			// try~catch문 안에 넣고 리턴값 int로 변경 
			memberRepository.save(member);
			result = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

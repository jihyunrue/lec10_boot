package com.gn.spring.security.vo;

import org.springframework.security.core.userdetails.User;

import com.gn.spring.member.domain.MemberDto;

import lombok.Getter;

@Getter
public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	private MemberDto dto;
	
	public SecurityUser(MemberDto dto) {
		super(dto.getMem_id(), dto.getMem_pw(),dto.getAuthorities());
		this.dto = dto;
	}
	
}
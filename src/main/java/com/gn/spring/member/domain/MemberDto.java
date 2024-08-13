package com.gn.spring.member.domain;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberDto {
	
	private Long mem_no;
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_auth;
	
	private List<GrantedAuthority> authorities;
	
	public Member toEntity() {
		return Member.builder()
				.memNo(mem_no)
				.memId(mem_id)
				.memPw(mem_pw)
				.memName(mem_name)
				.memAuth(mem_auth)
				.build();
	}
	
	public MemberDto toDto(Member member){
		return MemberDto.builder()
				.mem_no(member.getMemNo())
				.mem_id(member.getMemId())
				.mem_pw(member.getMemPw())
				.mem_name(member.getMemName())
				.mem_auth(member.getMemAuth())
				.build();
	}
	

}
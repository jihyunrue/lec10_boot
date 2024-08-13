package com.gn.spring.member.domain;

import java.util.List;

import com.gn.spring.board.domain.Board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="member")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mem_no")
	private Long memNo;
	
	@Column(name = "mem_id")
	private String memId;
	
	@Column(name = "mem_pw")
	private String memPw;
	
	@Column(name = "mem_name")
	private String memName;
	
	@Column(name = "mem_auth")
	private String memAuth;
	
	@OneToMany(mappedBy = "member")
	private List<Board> boards;
	
}

package com.gn.spring.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gn.spring.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{

	Member findBymemNo(Long board_no);
	
	Member findBymemId(String mem_id);
	
	@Query(value="SELECT m "
			+ "FROM Member m "
			+ "WHERE m.memId != ?1 "
			+ "AND m.memAuth != 'ADMIN' "
			+ "AND (m.memId NOT IN (SELECT cr.fromId "
			+ "FROM ChatRoom cr "
			+ "WHERE cr.toId = '?1')) "
			+ "AND (m.memId NOT IN (SELECT cr.toId "
			+ "FROM ChatRoom cr "
			+ "WHERE cr.fromId = '?1'))")
	List<Member> findAllForChat(String mem_id);
	
}
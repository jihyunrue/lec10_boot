package com.gn.spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gn.spring.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
}

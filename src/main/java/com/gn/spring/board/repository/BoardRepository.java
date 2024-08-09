package com.gn.spring.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gn.spring.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	Page<Board> findByboardTitleContaining(String keyword, Pageable pageable);
	
	Page<Board> findByboardContentContaining(String keyword, Pageable pageable);
	
	@Query(value="SELECT b FROM Board b "+
			     "WHERE b.boardTitle LIKE CONCAT('%',?1,'%') "+
			     "OR b.boardContent LIKE CONCAT('%',?1,'%') "+
			     "ORDER BY b.regDate DESC",
		   countQuery="SELECT COUNT(b) FROM Board b "+
					  "WHERE b.boardTitle LIKE CONCAT('%',?1,'%') "+
					  "OR b.boardContent LIKE CONCAT('%',?1,'%') ")
	Page<Board> findByboardTitleOrboardContentContaining(String keyword, Pageable pageable);
}

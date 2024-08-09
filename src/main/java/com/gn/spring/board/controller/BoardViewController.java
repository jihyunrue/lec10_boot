package com.gn.spring.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gn.spring.board.domain.BoardDto;
import com.gn.spring.board.service.BoardService;

@Controller
public class BoardViewController {
	
	private final BoardService boardService;
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardViewController.class);
	
	@Autowired
	public BoardViewController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	
	@GetMapping("/board")
	public String selectBoardList(Model model,
			@PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.DESC)Pageable pageable,
			BoardDto searchDto) {
		
		Page<BoardDto> resultList = boardService.selectBoardList(searchDto,pageable);
		LOGGER.debug(resultList.toString());
		model.addAttribute("resultList",resultList);
		model.addAttribute("searchDto",searchDto);
		return "board/list";
	}
}
package com.gn.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gn.spring.board.domain.BoardDto;
import com.gn.spring.board.service.BoardService;
import com.gn.spring.board.service.FileService;

@Controller
public class BoardApiController {
	
	private final FileService fileService;
	private final BoardService boardService;
	@Autowired
	public BoardApiController(FileService fileService,
			BoardService boardService) {
		this.fileService = fileService;
		this.boardService = boardService;
	}
	
	@GetMapping("/download/{board_no}")
	public ResponseEntity<Object> boaedImgDownload(
			@PathVariable("board_no")Long board_no){
		return fileService.download(board_no);
	}
	
	@ResponseBody
	@PostMapping("/board")
	public Map<String,String> createBoard(BoardDto dto,
			@RequestParam("file") MultipartFile file){
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "게시글 등록중 오류가 발생했습니다.");
		
		String savedFileName = fileService.upload(file);
		if(savedFileName != null) {
			dto.setOri_thumbnail(file.getOriginalFilename());
			dto.setNew_thumbnail(savedFileName);
			if(boardService.createBoard(dto) != null) {
				resultMap.put("res_code","200");
				resultMap.put("res_msg", "게시글이 성공적으로 등록되었습니다.");
			}
		}else {
			resultMap.put("res_msg", "파일 업로드가 실패하였습니다.");
		}
		return resultMap;
	}
	
}

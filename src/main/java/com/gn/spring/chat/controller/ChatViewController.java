package com.gn.spring.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gn.spring.chat.domain.ChatMsgDto;
import com.gn.spring.chat.domain.ChatRoomDto;
import com.gn.spring.chat.service.ChatService;
import com.gn.spring.member.domain.MemberDto;
import com.gn.spring.member.service.MemberService;

@Controller
public class ChatViewController {
	
	private final ChatService chatService;
	private final MemberService memberService;
	
	@Autowired
	public ChatViewController(ChatService chatService, MemberService memberService) {
		this.chatService = chatService;
		this.memberService = memberService;
	}
	
	@GetMapping("/chat/{room_no}")
	public String startChatting(@PathVariable("room_no") Long room_no ,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		ChatRoomDto dto = chatService.selectChatRoomOne(room_no, memId);
		model.addAttribute("dto",dto);
		
		List<ChatMsgDto> resultList = chatService.selectChatMsgList(room_no,memId);
		model.addAttribute("resultList",resultList);
		return "chat/detail";
	}
	
	@GetMapping("/chat/create")
	public String createChatRoomPage(Model model) {
		// 채팅방을 생성할 수 있는 회원 목록 조회
		// 1. 로그인한 사용자 제외
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		// 2. ADMIN 권한 X
		// 3. 내가 fromId일때 toId인 사람
		// 4. 내가 toId일때 fromId인 사람
		List<MemberDto> resultList = memberService.findAllForChat(memId);
		model.addAttribute("resultList",resultList);
	
		return "chat/create";
	}
	
	@GetMapping("/chat")
	public String selectChatRoom( Model model,
			@PageableDefault(page=0, size=10, sort="lastDate",direction=Sort.Direction.DESC) Pageable pageable) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		Page<ChatRoomDto> resultList = chatService.selectChatRoomList(pageable,memId);
		System.out.println("목록 전달 확인");
		System.out.println(resultList);
		model.addAttribute("resultList",resultList);
		
		return "chat/list";
	}

}
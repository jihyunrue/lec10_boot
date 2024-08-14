package com.gn.spring.chat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gn.spring.chat.domain.ChatRoomDto;
import com.gn.spring.chat.service.ChatService;

@Controller
public class ChatApiController {
	
	private final ChatService chatService;
	
	@Autowired
	public ChatApiController (ChatService chatService) {
		this.chatService = chatService;
	}
	
	@ResponseBody
	@PostMapping("/chat/create")
	public Map<String,String> createChatRoom(@RequestBody ChatRoomDto dto){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "채팅방 생성중 오류가 발생하였습니다.");
		
		if(chatService.createChatRoom(dto) > 0) {
			resultMap.put("res_code", "200");
			resultMap.put("res_msg", "성공적으로 채팅 생성이 완료되었습니다.");
		}
		return resultMap;
	}
	
}

package com.gn.spring.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gn.spring.chat.domain.ChatMsgDto;
import com.gn.spring.chat.service.ChatService;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler{
	
	private final ChatService chatService;
	@Autowired
	public ChatWebSocketHandler (ChatService chatService) {
		this.chatService = chatService;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		ObjectMapper objMapper = new ObjectMapper();
		ChatMsgDto dto = objMapper.readValue(payload, ChatMsgDto.class);
		chatService.createChatMsg(dto);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	
	}
	
	
	
}

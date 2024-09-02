package com.gn.spring.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gn.spring.chat.domain.ChatMsg;
import com.gn.spring.chat.domain.ChatRoom;

public interface ChatMsgRepository extends JpaRepository<ChatMsg,Long>{
	
	List<ChatMsg> findAllBychatRoom(ChatRoom chatRoom);
}

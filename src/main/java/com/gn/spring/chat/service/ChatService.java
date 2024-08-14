package com.gn.spring.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gn.spring.chat.domain.ChatRoom;
import com.gn.spring.chat.domain.ChatRoomDto;
import com.gn.spring.chat.repository.ChatRoomRepository;
import com.gn.spring.member.domain.Member;
import com.gn.spring.member.domain.MemberDto;
import com.gn.spring.member.repository.MemberRepository;

@Service
public class ChatService {
	
	private final ChatRoomRepository chatRoomRepository;
	private final MemberRepository memberRepository;
	
	@Autowired
	public ChatService(ChatRoomRepository chatRoomRepository, MemberRepository memberRepository) {
		this.chatRoomRepository = chatRoomRepository;
		this.memberRepository = memberRepository;
	}
	
	public int createChatRoom(ChatRoomDto dto) {
		int result = -1;
		try {
			ChatRoom cr = dto.toEntity();
			chatRoomRepository.save(cr);
			result = 1;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Page<ChatRoomDto> selectChatRoomList(Pageable pageable, String memId){
		Page<ChatRoom> chatRoomList = chatRoomRepository.findAllByfromIdAndtoId(memId,pageable);
		
		List<ChatRoomDto> chatRoomDtoList = new ArrayList<ChatRoomDto>();
		
		for(ChatRoom c : chatRoomList) {
			ChatRoomDto dto = new ChatRoomDto().toDto(c);
			
			// 상대방 이름 셋팅
			// 1. 지금 로그인한 사용자 == fromId -> 상대방 : toId
			if(memId.equals(dto.getFrom_id())) {
				
				// 상대방 아이디 -> 상대방 이름
				// 1. ChatRoomDto에 필드(not_me_name)추가
				// 2. MemberRepository한테 부탁해서 회원 정보 조회(아이디 기준)
				Member temp = memberRepository.findBymemId(dto.getTo_id());
				// 3. CharRoomDto의 not_me_name필드에 회원 이름 셋팅
				// 4. 목록 화면에 상대방 아이디 -> 이름
				dto.setNot_me_name(temp.getMemName());
				//dto.setNot_me_id(dto.getTo_id());
			} else {
				// 2. 지금 로그인한 사용자 == toId -> 상대방 : fromId
				Member temp = memberRepository.findBymemId(dto.getFrom_id());
				dto.setNot_me_name(temp.getMemName());
				//dto.setNot_me_id(dto.getFrom_id());
			}
			
			chatRoomDtoList.add(dto);
		}
		return new PageImpl<>(chatRoomDtoList,pageable,chatRoomList.getTotalElements());
	}

}
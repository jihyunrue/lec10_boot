package com.gn.spring.chat.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class ChatRoomDto {
	
	private Long room_no;
	private String from_id;
	private String to_id;
	private String last_msg;
	private LocalDateTime last_date;
	
	private String not_me_id;
	private String not_me_name;
	
	public ChatRoom toEntity() {
		return ChatRoom.builder()
				.roomNo(room_no)
				.fromId(from_id)
				.toId(to_id)
				.lastMsg(last_msg)
				.lastDate(last_date)
				.build();
	}
	
	public ChatRoomDto toDto(ChatRoom chatRoom) {
		return ChatRoomDto.builder()
				.room_no(chatRoom.getRoomNo())
				.from_id(chatRoom.getFromId())
				.to_id(chatRoom.getToId())
				.last_msg(chatRoom.getLastMsg())
				.last_date(chatRoom.getLastDate())
				.build();
	}

}
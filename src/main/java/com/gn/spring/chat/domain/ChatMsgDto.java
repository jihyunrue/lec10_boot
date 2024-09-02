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

public class ChatMsgDto {
	
	private Long chat_no;
	private String chat_content;
	private String is_from_sender;
	private String is_receiver_read;
	private LocalDateTime send_date;
	
	private String sender_id;   // 메시지 작성자(발신자)
	private String receiver_id; // 메시지 수신자
	private String me_flag;     // 로그인 사용자 == 작성자
	
	private Long room_no;
	
	public ChatMsg toEntity() {
		return ChatMsg.builder()
				.chatNo(chat_no)
				.chatContent(chat_content)
				.isFromSender(is_from_sender)
				.isReceiverRead(is_receiver_read)
				.sendDate(send_date)
				.build();
	}
	
	public ChatMsgDto toDto(ChatMsg chatMsg) {
		return ChatMsgDto.builder()
				.chat_no(chatMsg.getChatNo())
				.chat_content(chatMsg.getChatContent())
				.is_from_sender(chatMsg.getIsFromSender())
				.is_receiver_read(chatMsg.getIsReceiverRead())
				.send_date(chatMsg.getSendDate())
				.build();
	}
}

package com.gn.spring.chat.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="chat_room")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Builder
public class ChatRoom {
	
	@Id
	@Column(name="room_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomNo;
	
	@Column(name="from_id")
	private String fromId;
	
	@Column(name="to_id")
	private String toId;
	
	@Column(name="last_msg")
	private String lastMsg;
	
	@Column(name="last_date")
	private LocalDateTime lastDate;
	
	@OneToMany(mappedBy = "chatRoom")
	private List<ChatMsg> chatMsgs;

}
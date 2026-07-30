package com.grazyarcadeback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
  private String roomId;   // 방
  private String senderId; // 보낸 사람 ID
  private String content;  // 채팅 내용
}
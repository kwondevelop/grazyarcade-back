package com.grazyarcadeback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LobbyEventMessage {
  private String roomId;
  private String type;     // "JOIN", "LEAVE", "READY", "UNREADY", "HOST_CLAIM", "STATE_SYNC"
  private String senderId;
  private String payload;  // 세부 데이터를 담을 문자열
}
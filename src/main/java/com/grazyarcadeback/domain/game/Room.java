package com.grazyarcadeback.domain.game;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class Room {
  private String roomId;
  private String roomName;
  private int maxPlayers = 4; // 최대 인원 제한
  private int currentPlayers = 0;
  private boolean isPlaying = false; // 게임 진행 상태 여부

  public Room(String roomName) {
    this.roomId = UUID.randomUUID().toString(); // 고유 방 번호 자동 생성
    this.roomName = roomName;
  }
}
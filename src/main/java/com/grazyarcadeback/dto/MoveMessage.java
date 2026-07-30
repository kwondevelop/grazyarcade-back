package com.grazyarcadeback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveMessage {
  private String roomId;   // 방
  private String playerId; // 누구인지 식별 (예: "player1")
  private int x;           // 이동한 x 좌표
  private int y;           // 이동한 y 좌표
}
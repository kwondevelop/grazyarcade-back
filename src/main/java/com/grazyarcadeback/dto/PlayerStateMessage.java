package com.grazyarcadeback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStateMessage {
  private String roomId;
  private String playerId;
  private boolean isTrapped; // 물방울에 갇혔는지 여부
  private boolean isDead;    // 완전히 죽었는지 여부
}
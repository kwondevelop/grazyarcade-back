package com.grazyarcadeback.controller;

import com.grazyarcadeback.dto.BombMessage;
import com.grazyarcadeback.dto.MoveMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

  // 1. 캐릭터 이동 중계
  // 프론트엔드가 "/app/move"로 메시지를 보내면 이 메서드가 실행됨
  @MessageMapping("/move")
  // 처리된 결과를 "/topic/move"를 구독(듣고 있는)하는 모든 유저에게 뿌림
  @SendTo("/topic/move")
  public MoveMessage broadcastMove(MoveMessage message) {
    // 방(Room) 시스템을 붙이기 전, 가장 기초적인 전체 방송 로직입니다.
    // 들어온 메시지를 그대로 다시 튕겨내어 모두에게 전달합니다.
    return message;
  }

  // 2. 물풍선 설치 중계
  @MessageMapping("/bomb")
  @SendTo("/topic/bomb")
  public BombMessage broadcastBomb(BombMessage message) {
    return message;
  }
}
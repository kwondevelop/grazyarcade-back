package com.grazyarcadeback.controller;

import com.grazyarcadeback.dto.*;
import com.grazyarcadeback.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GameController {

  private final RoomService roomService;

  // 주소에 {roomId} 추가
  @MessageMapping("/room/{roomId}/move")
  @SendTo("/topic/room/{roomId}/move")
  public MoveMessage broadcastMove(@DestinationVariable String roomId, MoveMessage message) {
    return message;
  }

  @MessageMapping("/room/{roomId}/bomb")
  @SendTo("/topic/room/{roomId}/bomb")
  public BombMessage broadcastBomb(@DestinationVariable String roomId, BombMessage message) {
    return message;
  }

  @MessageMapping("/room/{roomId}/chat")
  @SendTo("/topic/room/{roomId}/chat")
  public ChatMessage broadcastChat(@DestinationVariable String roomId, ChatMessage message) {
    return message;
  }

  // 입장 알림 처리
  @MessageMapping("/room/{roomId}/enter")
  @SendTo("/topic/room/{roomId}/chat")
  public ChatMessage enterRoom(@DestinationVariable String roomId, ChatMessage message) {
    roomService.incrementPlayer(roomId); // 인원수 +1
    message.setContent(message.getSenderId() + "님이 입장하셨습니다.");
    message.setSenderId("System"); // 보낸 사람을 'System'으로 변경
    return message;
  }

  // 퇴장 알림 처리
  @MessageMapping("/room/{roomId}/leave")
  @SendTo("/topic/room/{roomId}/chat")
  public ChatMessage leaveRoom(@DestinationVariable String roomId, ChatMessage message) {
    roomService.decrementPlayer(roomId); // 인원수 -1
    message.setContent(message.getSenderId() + "님이 퇴장하셨습니다.");
    message.setSenderId("System");
    return message;
  }

  // 누군가 '게임 시작'을 누르면 상태를 바꾸고 모두에게 신호를 보냄
  @MessageMapping("/room/{roomId}/start")
  @SendTo("/topic/room/{roomId}/start")
  public String broadcastStart(@DestinationVariable String roomId) {
    roomService.startGame(roomId); // 서버 방 상태를 '게임 중'으로 변경 (중도 난입 차단)
    return "START"; // 같은 방 유저들에게 시작하라고 방송
  }

  // 플레이어 상태(갇힘, 죽음, 구출) 변경 중계
  @MessageMapping("/room/{roomId}/state")
  @SendTo("/topic/room/{roomId}/state")
  public PlayerStateMessage broadcastState(@DestinationVariable String roomId, PlayerStateMessage message) {
    return message;
  }

  // 방장 위임 및 레디 상태를 공유하는 통신망
  @MessageMapping("/room/{roomId}/lobby")
  @SendTo("/topic/room/{roomId}/lobby")
  public LobbyEventMessage broadcastLobby(@DestinationVariable String roomId, LobbyEventMessage message) {
    return message;
  }
}
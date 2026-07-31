package com.grazyarcadeback.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

  // DB 대신 메모리에 방 목록 임시 저장
  private static final Map<String, Map<String, Object>> rooms = new HashMap<>();

  @GetMapping
  public Collection<Map<String, Object>> getRooms() {
    return rooms.values();
  }

  @PostMapping
  public Map<String, Object> createRoom(@RequestBody Map<String, String> request) {
    String title = request.get("title");
    String host = request.get("host");
    String roomId = UUID.randomUUID().toString().substring(0, 8); // 랜덤 방 번호

    Map<String, Object> room = new HashMap<>();
    room.put("roomId", roomId);
    room.put("title", title);
    room.put("host", host);
    room.put("status", "WAITING");

    rooms.put(roomId, room);
    return room;
  }
}
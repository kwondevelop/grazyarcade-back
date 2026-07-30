package com.grazyarcadeback.controller;

import com.grazyarcadeback.domain.game.Room;
import com.grazyarcadeback.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*") // 프론트엔드(Vue)에서 오는 요청 허용
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  // 방 목록 조회 (GET /api/rooms)
  @GetMapping
  public List<Room> getRooms() {
    return roomService.findAllRooms();
  }

  // 방 생성 (POST /api/rooms?name=방제목)
  @PostMapping
  public Room createRoom(@RequestParam String name) {
    return roomService.createRoom(name);
  }
}
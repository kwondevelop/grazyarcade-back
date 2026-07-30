package com.grazyarcadeback.service;

import com.grazyarcadeback.domain.game.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomService {
  // 서버 메모리에 방 목록을 저장하는 해시맵 (동시성 문제를 위해 ConcurrentHashMap 사용)
  private final Map<String, Room> roomMap = new ConcurrentHashMap<>();

  // 1. 방 만들기
  public Room createRoom(String roomName) {
    Room room = new Room(roomName);
    roomMap.put(room.getRoomId(), room);
    return room;
  }

  // 2. 전체 방 목록 조회
  public List<Room> findAllRooms() {
    return new ArrayList<>(roomMap.values());
  }

  // 3. 특정 방 조회
  public Room findRoomById(String roomId) {
    return roomMap.get(roomId);
  }

  // 4. 방 인원 관리
  public void incrementPlayer(String roomId) {
    Room room = roomMap.get(roomId);
    if (room != null && room.getCurrentPlayers() < room.getMaxPlayers()) {
      room.setCurrentPlayers(room.getCurrentPlayers() + 1);
    }
  }

  public void decrementPlayer(String roomId) {
    Room room = roomMap.get(roomId);
    if (room != null && room.getCurrentPlayers() > 0) {
      room.setCurrentPlayers(room.getCurrentPlayers() - 1);
    }
  }

  public void startGame(String roomId) {
    Room room = roomMap.get(roomId);
    if (room != null) {
      room.setPlaying(true);
    }
  }
}
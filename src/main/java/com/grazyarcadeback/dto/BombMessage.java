package com.grazyarcadeback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BombMessage {
  private String playerId; // 물풍선을 설치한 유저
  private int x;           // 설치한 x 좌표
  private int y;           // 설치한 y 좌표
  private int power;       // 물줄기 길이
}
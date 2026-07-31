package com.grazyarcadeback.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users") // "user"는 DB 예약어일 수 있으므로 "users"로 테이블명 지정
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 50)
  private String username; // 로그인 아이디

  @Column(nullable = false)
  private String password; // 비밀번호

  @Column(unique = true, nullable = false, length = 30)
  private String nickname; // 게임 내에서 보여질 닉네임

  // 전적 및 스탯 정보
  private int winCount = 0;
  private int loseCount = 0;
  private int level = 1;
}
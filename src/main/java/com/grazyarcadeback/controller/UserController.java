package com.grazyarcadeback.controller;

import com.grazyarcadeback.dto.SignupRequest;
import com.grazyarcadeback.dto.LoginRequest;
import com.grazyarcadeback.domain.user.User;
import com.grazyarcadeback.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
    try {
      userService.signup(request);
      return ResponseEntity.ok("회원가입이 완료되었습니다.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
    try {
      User user = userService.login(request);

      // 로그인 성공 시 세션 생성 (입장권 발급)
      HttpSession session = httpRequest.getSession(); // 기존 세션이 없으면 새로 생성
      session.setAttribute("loginUser", user);        // 세션 메모리에 유저 정보 저장

      Map<String, Object> response = new HashMap<>();
      response.put("message", "로그인 성공");
      response.put("nickname", user.getNickname());

      return ResponseEntity.ok(response);

    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(401).body(e.getMessage());
    }
  }

  // 현재 로그인 상태(세션) 확인 API
  @GetMapping("/me")
  public ResponseEntity<?> checkSession(HttpServletRequest httpRequest) {
    HttpSession session = httpRequest.getSession(false); // 세션이 없으면 새로 만들지 않고 null 반환

    if (session != null && session.getAttribute("loginUser") != null) {
      User user = (User) session.getAttribute("loginUser");
      return ResponseEntity.ok(user);
    }

    return ResponseEntity.status(401).body("로그인 상태가 아닙니다.");
  }

  // 로그아웃 API
  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest httpRequest) {
    HttpSession session = httpRequest.getSession(false);
    if (session != null) {
      session.invalidate(); // 세션 데이터 삭제 및 무효화
    }
    return ResponseEntity.ok("로그아웃 되었습니다.");
  }

  @PostMapping("/result")
  public ResponseEntity<?> recordResult(@RequestBody com.grazyarcadeback.dto.GameResultRequest request) {
    userService.updateStats(request.getWinnerNickname(), request.getLoserNicknames());
    return ResponseEntity.ok("전적이 기록되었습니다.");
  }
}
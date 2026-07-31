package com.grazyarcadeback.service;

import com.grazyarcadeback.domain.user.User;
import com.grazyarcadeback.repository.UserRepository;
import com.grazyarcadeback.dto.SignupRequest;
import com.grazyarcadeback.dto.LoginRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  // 회원가입 로직
  public void signup(SignupRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
    }
    if (userRepository.existsByNickname(request.getNickname())) {
      throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword()); // (참고: 실무에서는 비밀번호를 그대로 넣지 않고 BCrypt 등으로 암호화합니다)
    user.setNickname(request.getNickname());

    userRepository.save(user);
  }

  // 로그인 로직
  public User login(LoginRequest request) {
    User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));

    if (!user.getPassword().equals(request.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    return user;
  }

  // 전적 업데이트 로직
  @org.springframework.transaction.annotation.Transactional
  public void updateStats(String winnerNickname, java.util.List<String> loserNicknames) {
    // 승자 처리
    if (winnerNickname != null && !winnerNickname.equals("DRAW") && !winnerNickname.equals("NONE")) {
      userRepository.findByNickname(winnerNickname).ifPresent(user -> {
        user.setWinCount(user.getWinCount() + 1);
        // 5승마다 1레벨업
        if (user.getWinCount() % 5 == 0) {
          user.setLevel(user.getLevel() + 1);
        }
      });
    }
    // 패자 처리
    if (loserNicknames != null) {
      for (String loser : loserNicknames) {
        userRepository.findByNickname(loser).ifPresent(user -> {
          user.setLoseCount(user.getLoseCount() + 1);
        });
      }
    }
  }
}
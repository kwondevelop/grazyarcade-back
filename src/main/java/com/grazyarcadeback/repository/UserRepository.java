package com.grazyarcadeback.repository;

import com.grazyarcadeback.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  // 로그인할 때 아이디로 유저를 찾기 위한 메서드
  Optional<User> findByUsername(String username);
  Optional<User> findByNickname(String nickname);

  // 중복 가입 방지를 위한 존재 여부 확인 메서드
  boolean existsByUsername(String username);
  boolean existsByNickname(String nickname);
}
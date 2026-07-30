package com.grazyarcadeback.config; // 본인의 실제 패키지명에 맞게 수정하세요

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // 1. Vue.js가 처음 웹소켓에 접속할 주소 (엔드포인트) 설정
    registry.addEndpoint("/ws-stomp")
        .setAllowedOriginPatterns("*") // CORS 허용 (Vue의 접근을 허락)
        .withSockJS(); // 구버전 브라우저에서도 웹소켓이 작동하도록 지원
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 2. 메시지를 발행(Publish)할 때 붙이는 접두사 설정
    // 클라이언트(Vue)가 서버로 데이터를 보낼 때 "/app"으로 시작하는 주소로 보냄
    registry.setApplicationDestinationPrefixes("/app");

    // 3. 메시지를 구독(Subscribe)할 때 붙이는 접두사 설정
    // 서버가 클라이언트(Vue)들에게 데이터를 뿌려줄 때 "/topic"으로 시작하는 주소로 보냄
    registry.enableSimpleBroker("/topic");
  }
}
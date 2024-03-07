package com.example.demo.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class webSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // enableSimpleBroker() : 메모리기반 메세지 브로커가 클라이언트에게 메시지 전달
    config.enableSimpleBroker("/topic");
    // setApplicationDestinationPrefixes() : 서버에서 클라이언트로부터 메시지를 받을 api의 prefix를 설정
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // registerStompEndpoints() : 클라이언트에서 WebSocket을 연결할 api설정
    // StompEndpointRegistry의 메서드인 addEndpoint()를 통해 여러가지 end point를 설정
    registry.addEndpoint("/stomp-endpoint").setAllowedOriginPatterns("*")
            .withSockJS();
  }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class socketController {

  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;

  @RequestMapping("/socket")
  public String testSocket() {
    return "socket";
  }

  // 아래는 발행과 구독이 같은 곳에서 (한마디로 다른 브라우저지만 같은 동작을 해야할 때) 같은 데이터를 주고받는걸 테스트
  @RequestMapping(value = "/testCtrl")
  public void testCtrl() {
    ctrl("{name: test, val: 11}");
  } // 직접 값을 줌으로써 트리거역할을 함

  public void ctrl(String data) {
    simpMessagingTemplate.convertAndSend("/topic/ctrl", data);
  }

  // 얘는 publish할때 목적지가 됨으로써 값을 전해주는 역할
  @MessageMapping(value = "/testRecv")
  public void testRecv(String data) {
    System.out.println(data);
    simpMessagingTemplate.convertAndSend("/topic/ctrl", "그래 안녕!");
  }

}






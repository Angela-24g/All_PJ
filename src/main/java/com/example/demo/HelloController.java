package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
  // 시큐리티 로그인, 디비 연결로 우리 화면 하나만 만들기 ( css, db 연결(완료), 소켓통신 ) - 시큐리티를 config로 해서 나와야 함
  // 엑셀 다운로드,

  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;

  @RequestMapping("/testOne")
  public String testOne() {
    return "index";

  }

   @RequestMapping(value = "/testCtrl")
  public void testCtrl(){
    ctrl("{name: test, val: 11}");
  }

  public void ctrl(String data) {
    simpMessagingTemplate.convertAndSend("/topic/ctrl", data);
  }

  @MessageMapping(value = "/testRecv")
  public void testRecv(){
    simpMessagingTemplate.convertAndSend("/topic/ctrl", "test12");
  }

//test.send("testRecv", {}, "{test:1}");
// test.publish({destination: "/app/testRecv", body: "Hello, STOMP"});

}

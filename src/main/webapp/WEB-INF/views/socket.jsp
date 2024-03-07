<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>WebSocket</title>
  <script type="text/javascript" src="/bundle/sockjs/sockjs.min.js"></script>
  <script type="text/javascript" src="/bundle/stompjs/stomp.umd.js"></script>
  <script>
    function fn_sock() {
      var client = new StompJs.Client({
        webSocketFactory: () => new SockJS("stomp-endpoint"),
        onConnect: () => {
          client.subscribe('/topic/ctrl', message => {
                document.getElementById("test2").value = message.body;
              }
          );
          client.publish({ destination: '/app/testRecv', body: document.getElementById("test1").value });
          // 주제의 구독자들에게 메시지가 전송되는 것, 토픽 발행에 대한 동작(self-test)
        },
      });
      client.activate();
    }

  </script>
</head>
<body>
<div class="divClass">
  <input type="text" id="test1" style="width: 500px; height: 20px;">
  <button onclick="fn_sock()" name="button" style="width: 20px; height: 20px;"></button>
  <input type="text" id="test2" style="width: 500px; height: 20px;">
</div>
</body>
</html>
<%-- 소켓관련
단방향 통신
기존은, 계속 호출하여 변경된 데이터가 있는지 확인
서버에서 클라이언트로 변경이 필요한 데이터를 푸쉬(SSE - server sent events)

endpoint를 별도로 분리해서 관리--%>
<%--


test.subscribe("/topic/ctrl", message => {
console.info(message);
});

test.send("testRecv", {}, "{test:1}");

test.publish({destination: "/app/testRecv", body: "Hello, STOMP"});--%>

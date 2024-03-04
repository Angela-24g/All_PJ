<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>HiHi</title>
  <%--<script type="text/javascript" src="/bundle/jquery-1.11.2/jquery.min.js"></script>--%>
  <script type="text/javascript" src="/bundle/stompjs/stomp.umd.js"></script>
  <script type="text/javascript" src="C:\dev\demo\src\main\resources\static.bundle.sockjs"></script>
  <script>
    function fn_sock() {
      var sock = new sockJS
      /*  var client = new StompJs({
        webSocketFactory: ()=> new SockJS("stomp-endpoint"),
        onConnect : () => {
          console.log("onConnect");
        }
      });
      client.activate();
    */}
  </script>
</head>
<body>
  <div class="divClass">
    <input type="text" style="width: 500px; height: 20px;">
    <button onclick="fn_sock()" name="button" style="width: 20px; height: 20px;"></button>
  </div>
</body>
</html>
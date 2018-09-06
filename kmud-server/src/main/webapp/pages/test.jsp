<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	test.jsp
	
	<script>
	var ws = new WebSocket("ws://localhost:8080/websocket");
    
    ws.onopen = function()
    {
       // Web Socket 已连接上，使用 send() 方法发送数据
       ws.send("发送数据");
       alert("数据发送中...");
    };
     
    ws.onmessage = function (evt) 
    { 
       var received_msg = evt.data;
       alert("数据已接收...");
    };
     
    ws.onclose = function()
    { 
       // 关闭 websocket
       alert("连接已关闭..."); 
    };
	</script>
</body>
</html>
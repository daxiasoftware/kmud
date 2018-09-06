package com.daxia.mud.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.alibaba.fastjson.JSONObject;
import com.daxia.mud.config.SpringContextHolder;
import com.daxia.mud.entity.Room;
import com.daxia.mud.service.RoomService;
import com.daxia.mud.service.WebSocketService;

//@CrossOrigin(origins = "http://localhost:8081")
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket extends SpringBeanAutowiringSupport{
	
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<SessionDTO> webSocketSet = new CopyOnWriteArraySet<SessionDTO>();
    
    
    private static Map<String, SessionDTO> userSessionMap = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    // private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
    	SessionDTO sessionDTO = new SessionDTO(session);
    	WebSocketService.sessionMap.put(session, sessionDTO);
        webSocketSet.add(sessionDTO);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
            //sendMessage(session, "hello");
    }

    
	/**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
    	System.out.println("onclose: " + session);
        //webSocketSet.remove(this);  //从set中删除
    	WebSocketService.sessionMap.remove(session);
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @Autowired
    private WebSocketService webSocketService;
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	//WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
    	//WebSocketService service = wac.getBean(WebSocketService.class);
    	SpringContextHolder.getBean(WebSocketService.class).onMessage(message, session);
    }

    /**
     * 发生错误时调用
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public void sendInfo(String message) throws IOException {
        for (SessionDTO item : webSocketSet) {
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
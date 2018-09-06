package com.daxia.mud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
/*
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new WebSocketHandler() {
			
			@Override
			public boolean supportsPartialMessages() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
				System.out.println("handleMessage...");
			}
			
			@Override
			public void afterConnectionEstablished(WebSocketSession session) throws Exception {
				System.out.println("afterConnectionEstablished...");
			}
			
			@Override
			public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
				// TODO Auto-generated method stub
				
			}
		}, "/websocket").setAllowedOrigins("http://localhost:8081");
	}
  */
}

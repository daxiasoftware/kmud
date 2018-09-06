package com.daxia.mud.websocket;

import javax.websocket.Session;

public class SessionDTO {

	private Session session;
	private boolean logined;
	private String username;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public SessionDTO(Session session) {
		super();
		this.session = session;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}

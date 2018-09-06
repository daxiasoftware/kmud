package com.daxia.mud.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.daxia.mud.service.WebSocketService;

// @Component
public class MessageJob {
	
	@Autowired
	private WebSocketService webSocketService;

	@Scheduled(fixedRate = 2000)
	public void run() {
		System.out.println("message job...");
		JSONObject json = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		json.put("type", "chat");
		json.put("content", dateFormat.format(new Date()));
		webSocketService.sendAll(json.toJSONString());
	}
}

package com.daxia.mud.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daxia.mud.entity.Artifact;
import com.daxia.mud.entity.Bag;
import com.daxia.mud.entity.Player;
import com.daxia.mud.entity.Room;
import com.daxia.mud.websocket.SessionDTO;

@Service
public class WebSocketService {
	@Autowired
	private RoomService roomService;
	
	public static Map<Session, SessionDTO> sessionMap = new ConcurrentHashMap<>();
	
	public void sendAll(String message) {
		for (Session session : sessionMap.keySet()) {
			sendMessage(session, message);
		}
	}
	
	public void onMessage(String message, Session session) {
    	System.out.println("onmessage, roomService: " + roomService);
        System.out.println("来自客户端的消息:" + message);
        SessionDTO sessionDTO = sessionMap.get(session);
        //
        JSONObject json = JSONObject.parseObject(message);
        
        if (!"login".equals(json.getString("type")) && !sessionDTO.isLogined()) {
        	JSONObject ret = new JSONObject();
        	ret.put("type", "login");
        	sendMessage(session, ret.toJSONString());
        	return;
        } 
        String type = json.getString("type");
        if ("login".equals(type)) {
        	JSONObject ret = new JSONObject();
        	ret.put("type", "logined");
        	sessionDTO.setLogined(true);
        	sendMessage(session, ret.toJSONString());
        	return;
        } else if ("status".equals(type)) {
        	JSONObject ret = new JSONObject();
        	ret.put("type", type);
        	ret.put("exp", 1000);
        	ret.put("hp", 100);
        	sendMessage(session, ret.toJSONString());
        	return;
        } else if ("room".equals(type)) {
        	Room room = roomService.findByPayer(1L);
        	JSONObject ret = buildRoomJson(room);
        	sendMessage(session, ret.toJSONString());
        	return;
        } else if ("goroom".equals(type)) {
        	Room room = new Room().selectById(json.getLong("toRoomId"));
        	Player player = new Player().selectById(1l);
        	player.setX(room.getX());
        	player.setY(room.getY());
        	player.setRoomId(room.getId());
        	player.updateById();
        	JSONObject ret = buildRoomJson(room);
        	sendMessage(session, ret.toJSONString());
        	return;
        } else if ("pick".equals(type)) {
        	Bag bag = new Bag();
        	bag.setPlayerId(1L);
        	bag.setArtifactId(json.getLong("id"));
        	bag.insert();
        	JSONObject ret = new JSONObject();
        	ret.put("type", "pick");
        	sendMessage(session, ret.toJSONString());
        	return;
        } else if ("bag".equals(type)) {
        	JSONObject ret = new JSONObject();
        	ret.put("type", "bag");
        	List<Artifact> artifacts = roomService.findArtifactsByPlayer(1L);
        	JSONArray as = buildArtifactsJson(artifacts);
    		ret.put("artifacts", as);
        	sendMessage(session, ret.toJSONString());
        	return;
        }
	}

	private JSONObject buildRoomJson(Room room) {
		JSONObject ret = new JSONObject();
		ret.put("type", "room");
		ret.put("id", room.getId());
		ret.put("name", room.getName());
		ret.put("description", room.getDescription());
		ret.put("exits", roomService.findExitByRoom(room.getId()));
		List<Artifact> artifacts = roomService.findArtifactsByRoom(room.getId());
		JSONArray as = buildArtifactsJson(artifacts);
		ret.put("artifacts", as);
		return ret;
	}

	private JSONArray buildArtifactsJson(List<Artifact> artifacts) {
		JSONArray as = new JSONArray();
		for (Artifact artifact : artifacts) {
			JSONObject j = new JSONObject();
			j.put("id", artifact.getId());
			j.put("name", artifact.getName());
			j.put("level", artifact.getLevel());
			as.add(j);
		}
		return as;
	}
	
	private void sendMessage(Session session, String message) {
    	try {
    	 session.getBasicRemote().sendText(message);
    	} catch (Exception e) {
    		e.printStackTrace();
		}

	}
}

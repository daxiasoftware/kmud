package com.daxia.mud.controller;


import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.mud.entity.Artifact;
import com.daxia.mud.entity.Exits;
import com.daxia.mud.entity.Room;
import com.daxia.mud.entity.RoomArtifact;
import com.daxia.mud.service.RoomService;

/**
 * <p>
 * 房间 前端控制器
 * </p>
 *
 * @author daxia
 * @since 2018-08-29
 */
@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired(required = true)
	private RoomService roomService;
	
	@Transactional
	@ResponseBody
	@RequestMapping("hi")
	public String hi() throws Exception {
		System.out.println("hi, roomService: " + roomService);
		//Artifact artifact = new Artifact();
		//artifact.setName("木棍");
		//artifact.setLevel(1);
		//artifact.insert();
		for (int i = 2; i < 100; i++) {
			/*
			RoomArtifact roomArtifact = new RoomArtifact();
			roomArtifact.setArtifactId(1L);
			roomArtifact.setRoomId((long)i);
			roomArtifact.insert(); */
			/*
			Room south = new Room().selectById((long)i);
			Room north = new Room().selectById((long)(i + 1));
			
			Exits eNorth = new Exits();
			eNorth.setFromRoomId(south.getId());
			eNorth.setToRoomId(north.getId());
			eNorth.setType(3);
			eNorth.setDirection("N");
			eNorth.setName(north.getName());
			eNorth.insert();
			

			Exits sSouth = new Exits();
			sSouth.setFromRoomId(north.getId());
			sSouth.setToRoomId(south.getId());
			sSouth.setType(3);
			sSouth.setDirection("S");
			sSouth.setName(south.getName());
			sSouth.insert(); */
			/*
			Room r = new Room();
			r.setName("第" + i + "个房间");
			r.setDescription(r.getName() + "的描述");
			r.setX(i);
			r.setY(i);
			r.setNeedLevel(1);
			r.setMode(1);
			r.setType(2);
			r.insert(); */
		}
		return "hi";
	}
}


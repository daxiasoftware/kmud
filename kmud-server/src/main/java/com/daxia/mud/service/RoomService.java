package com.daxia.mud.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.daxia.mud.entity.Artifact;
import com.daxia.mud.entity.Exits;
import com.daxia.mud.entity.Player;
import com.daxia.mud.entity.Room;
import com.daxia.mud.mapper.RoomMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author daxia
 * @since 2018-08-29
 */
@Service
public class RoomService extends MyBaseService<RoomMapper, Room> {
	private List<Room> rooms;
	private List<Exits> exits;
	
	@PostConstruct
	public void init() {
		rooms = new Room().selectAll();
		//exits = new Exits().selectAll();
	}

	public Room findByPayer(long id) {
		Player p = new Player().selectById(id);
		for (Room room : rooms) {
			if (room.getId().longValue() == p.getRoomId()) {
				return room;
			}
 		}
		return null;
	}

	public List<Exits> findExitByRoom(Long roomId) {
		if (exits == null) {
			exits = new Exits().selectAll();
		}
		List<Exits> list = new ArrayList<>();
		for (Exits exit : exits) {
			if (exit.getFromRoomId().longValue() == roomId) {
				list.add(exit);
			}
		}
		return list;
	}
	
	public List<Artifact> findArtifactsByRoom(Long roomId) {
		String sql = "select a.* from room_artifact ra left join artifact a on ra.artifact_id = a.id where ra.room_id = ?";
		List<Artifact> artifacts = super.findBySql(sql, new Object[] {roomId}, Artifact.class);
		return artifacts;
	}

	public List<Artifact> findArtifactsByPlayer(long playerId) {
		String sql = "select a.* from bag ra left join artifact a on ra.artifact_id = a.id where ra.player_id = ?";
		List<Artifact> artifacts = super.findBySql(sql, new Object[] {playerId}, Artifact.class);
		return artifacts;
	}
}

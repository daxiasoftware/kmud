package com.daxia.mud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 房间
 * </p>
 *
 * @author daxia
 * @since 2018-08-29
 */
public class Room extends Model<Room> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Integer x;

    private Integer y;

    /**
     * 需要多少等级才能进入
     */
    private Integer needLevel;

    /**
     * 1，和平，2可战斗
     */
    private Integer mode;

    /**
     * 1，玩家；2，房间；3，出口；4，npc，5；怪物
     */
    private Integer type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getX() {
        return x;
    }
	

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getNeedLevel() {
        return needLevel;
    }

    public void setNeedLevel(Integer needLevel) {
        this.needLevel = needLevel;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Room{" +
        ", id=" + id +
        ", name=" + name +
        ", description=" + description +
        ", x=" + x +
        ", y=" + y +
        ", needLevel=" + needLevel +
        ", mode=" + mode +
        ", type=" + type +
        "}";
    }
}

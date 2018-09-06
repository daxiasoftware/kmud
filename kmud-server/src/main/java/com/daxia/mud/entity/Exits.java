package com.daxia.mud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 出口
 * </p>
 *
 * @author daxia
 * @since 2018-08-29
 */
public class Exits extends Model<Exits> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long fromRoomId;

    private Long toRoomId;

    private Integer type;

    /**
     * 方向, N, S, W, E
     */
    private String direction;

    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromRoomId() {
        return fromRoomId;
    }

    public void setFromRoomId(Long fromRoomId) {
        this.fromRoomId = fromRoomId;
    }

    public Long getToRoomId() {
        return toRoomId;
    }

    public void setToRoomId(Long toRoomId) {
        this.toRoomId = toRoomId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Exit{" +
        ", id=" + id +
        ", fromRoomId=" + fromRoomId +
        ", toRoomId=" + toRoomId +
        ", type=" + type +
        ", direction=" + direction +
        ", name=" + name +
        "}";
    }
}

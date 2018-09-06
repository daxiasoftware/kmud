package com.daxia.mud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class MyBaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "hiding", "rawtypes" })
	protected <K> K findOneBySql(String sql, Object[] params, Class<K> clz) {
		K t = (K) jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper(clz));
		return t;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <K> List<K> findBySql(String sql, Object[] params, Class<K> clz) {
		return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(clz));
	}
	
	protected int execute(String sql, Object[] params) {
		return jdbcTemplate.update(sql, params);
	}
}

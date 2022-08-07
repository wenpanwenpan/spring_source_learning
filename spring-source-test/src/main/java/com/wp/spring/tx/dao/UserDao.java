package com.wp.spring.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @description:
 * @author: panfeng.wen@hand-china
 * @create: 2021/03/08 18:31
 */
@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insert(){
		String sql = "insert into tbl_user(username,age) VALUES(?,?)";
		String username = UUID.randomUUID().toString().substring(0, 5);
		this.jdbcTemplate.update(sql,username,19);
	}
}

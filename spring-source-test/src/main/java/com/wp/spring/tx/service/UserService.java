package com.wp.spring.tx.service;

import com.wp.spring.tx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: panfeng.wen@hand-china
 * @create: 2021/03/08 18:30
 */
@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Transactional(rollbackFor = Exception.class)
	public void insert(){
		this.userDao.insert();
		int  i = 10 / 0;
		System.out.println("插入完成.....");
	}
}

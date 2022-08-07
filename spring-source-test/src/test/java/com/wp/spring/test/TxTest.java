package com.wp.spring.test;

import com.wp.spring.tx.config.TxConfig;
import com.wp.spring.tx.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: panfeng.wen@hand-china
 * @create: 2021/03/08 18:37
 */
public class TxTest {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = applicationContext.getBean(UserService.class);
		userService.insert();
		applicationContext.close();
	}
}

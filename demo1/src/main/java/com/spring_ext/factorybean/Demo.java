package com.spring_ext.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Demo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(AppConfig.class);
		app.start();
		PayService bean = (PayService) app.getBean(PayService.class);
		bean.pay();


//		Class[] classes = {PayService.class};
//		PayService o = (PayService) Proxy.newProxyInstance(Demo.class.getClassLoader(), classes, new ProxyHandler());
//		o.pay();
//

	}
}

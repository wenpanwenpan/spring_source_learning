package com.wp.spring.tx.test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.ws.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 构造函数循环依赖问题
 *
 * @author Mr_wenpan@163.com 2022/02/15 20:04
 */
public class App60_1 {

	public static void main(String[] args) {
		// 创建容器
		GenericApplicationContext context = new GenericApplicationContext();
		// 往容器里注册a和b
		context.registerBean("a",A.class);
		context.registerBean("b",B.class);

		// 使用工具类，往容器里注册一些基础注解、配置的后置处理器
		AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());;

		// 容器启动刷新
		context.refresh();
	}

	static class A {

		// 属性注入
		@Autowired
		private B b;

		/**
		 * 构造器注入
		 */
		public A(B b){
			ArrayList<String> list = new ArrayList<>();
			System.out.println("A(B b) " + b.getClass());
			this.b = b;
		}

		// setter方法注入
		@Autowired
		public void setB(B b) {
			this.b = b;
		}

		@PostConstruct
		public void init(){
			System.out.println("A init()....");
		}
	}

	static class B {

		private A a;

		/**
		 * 构造器注入
		 */
		public B(A a){
			System.out.println("B(A a) " + a.getClass());
			this.a = a;
		}

		@PostConstruct
		public void init(){
			System.out.println("B init()....");
		}
	}
}

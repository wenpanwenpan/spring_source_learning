package com.spring_util.reflection;

import com.entity.Dog;
import com.spring_util.entity.ICBCPay;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import static org.junit.Assert.*;

/**
 * @author pengYongQiang
 * @date 2020/11/21 11:19
 */
public class DemoTest {

	@Test
	public void a(){
		//仅当前类，不包括父类的属性
		ReflectionUtils.doWithLocalFields(ICBCPay.class, field -> {
			System.out.println(field.getName());
		});
		//当前类和包括父类的属性
		System.out.println("---------------");
		ReflectionUtils.doWithFields(ICBCPay.class,field -> {
			System.out.println(field.getName());
		});
	}
}
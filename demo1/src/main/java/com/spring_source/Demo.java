package com.spring_source;

import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;


public class Demo {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {

		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);

	}

	@Configuration
	public static class Config {

		@EventListener
		public void init(ContextRefreshedEvent contextRefreshedEvent) {
			System.out.println(123);

		}

	}
}

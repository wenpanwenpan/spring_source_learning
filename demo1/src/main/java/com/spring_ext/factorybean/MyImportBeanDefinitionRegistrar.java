package com.spring_ext.factorybean;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(PayService.class);
		AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();

		//beanDefinition.setBeanClass(MyFactoryBean.class);
		beanDefinition.setFactoryBeanName(MyFactoryBean.class.getName());

		registry.registerBeanDefinition("pay",beanDefinition);

	}
}

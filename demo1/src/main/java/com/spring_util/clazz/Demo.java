package com.spring_util.clazz;

import com.entity.Cat;
import com.entity.Dog;
import com.spring_source.DogServiceImpl;
import com.spring_source.IService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;
/**
 * @author pengYongQiang
 * @date 2020/9/21 22:58
 *
 */
public class Demo<T> {

	public static void main(String[] args) {
		Demo<Dog> dogDemo = new Demo<>();

		Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(IService.class, DogServiceImpl.class);
		System.out.println(classes);

		Class<?> [] typeParams = GenericTypeResolver.resolveTypeArguments(Integer.class,Comparable.class);
		System.out.println(Arrays.asList(typeParams));
	}

}

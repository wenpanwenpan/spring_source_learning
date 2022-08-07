package com.wp.spring.tx.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 事务原理测试
 *
 * @author Mr_wenpan@163.com 2022/02/15 16:11
 */
public class App64_1 {

	public static void main(String[] args) {

		test01();

	}

	public static void test02(){
		// 创建容器
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBean("aspect1",Aspect1.class);
		context.registerBean("aspect2",Aspect2.class);
		context.registerBean("aspect3",Aspect3.class);

		// 自动代理创建的后置处理器(作用：根据切点表达式，看看有没有和切点表达式匹配的目标，如果有则为这些类创建代理对象)
		context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);

		// 观察这个类的wrapIfNecessary方法，这个方法就是创建代理的方法
		AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
		// AnnotationAwareAspectJAutoProxyCreator类为切点切到的类创建代理对象，还是用的ProxyFactory


	}

	public static void test01(){
		// 创建容器
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBean("aspect1",Aspect1.class);
		context.registerBean("aspect2",Aspect2.class);
		context.registerBean("aspect3",Aspect3.class);

		// 自动代理创建的后置处理器(作用：根据切点表达式，看看有没有和切点表达式匹配的目标，如果有则为这些类创建代理对象)
		context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);

		context.registerBean("target1",Target1.class);
		context.registerBean("target2",Target2.class);

		context.refresh();

		Target1 target1 = context.getBean(Target1.class);
		Target2 target2 = context.getBean(Target2.class);

		// 调用代理对象的foo方法或bar方法时，就会从代理对象的属性中，找到这个代理对象关联的那些Advisors
		// 再经过advisor的pointcut属性去匹配切点表达式，如果匹配到了就会调用这个advisor
		target1.foo();
		System.out.println("========>>>>>>>>>>>>>>");
		target2.bar();
	}

	static class Target1{
		public void foo(){
			System.out.println("Target1 foo....");
		}
	}

	static class Target2{
		public void bar(){
			System.out.println("Target2 bar....");
		}
	}

	@Aspect
	static class Aspect1{
		// ----> 一个advisor增强器对象
		@Around("execution(* foo())")
		public Object around(ProceedingJoinPoint pjp) throws Throwable {
			System.out.println("Aspect1 around.....");
			return pjp.proceed();
		}
	}

	@Aspect
	static class Aspect2{
		// ----> 一个advisor增强器对象
		@Before("execution(* foo())")
		public void before() throws Throwable {
			System.out.println("Aspect2 before.....");
		}

		// ----> 一个advisor增强器对象
		@After("execution(* foo())")
		public void after() throws Throwable {
			System.out.println("Aspect2 after.....");
		}
	}

	@Aspect
	static class Aspect3{

		// ----> 一个advisor增强器对象
		@Before("execution(* foo())")
		public void before() throws Throwable {
			System.out.println("Aspect3 before.....");
		}
	}
}

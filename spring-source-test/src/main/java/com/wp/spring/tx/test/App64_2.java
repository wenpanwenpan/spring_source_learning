package com.wp.spring.tx.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 事务原理简单测试
 * aspect(切面) = 通知（advice） + 切点（pointCut），一个切面类里可能有多个通知方法
 * advisor 更细粒度的切面，只包含了一个切面 和 切点
 * 切面：比如@before、@after、@around标注的方法就是一个切面
 *
 * @author Mr_wenpan@163.com 2022/02/15 15:33
 */
public class App64_2 {

	public static void main(String[] args) {
		test01();

	}

	/**
	 * 代理的运行核心
	 * 代理对象中包含了能够使用advisor对象，执行目标前后先执行一个个的增强器
	 */
	public static void test02(){
		ProxyFactory proxyFactory = new ProxyFactory();
		// 设置管理的目标对象
		proxyFactory.setTarget(new Target1());
		// 添加一个切入点，可以动态的切入自己指定的位置
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		// 指定只切入foo方法
		pointcut.setExpression("execution(* foo())");

		// 添加一个增强器Advisor，将切面和切点进行关联
		proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, (MethodInterceptor) invocation -> {
			System.out.println("before1.....");
			Object proceed = invocation.proceed();
			System.out.println("after1.....");
			return proceed;
		}));

		// 添加第二个增强器
		proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, (MethodInterceptor) invocation -> {
			System.out.println("before2.....");
			Object proceed = invocation.proceed();
			System.out.println("after2.....");
			return proceed;
		}));

		// 设置代理类需要的接口（设置了以后就会创建JDK代理而不是CGlib代理）
//		proxyFactory.addInterface(I1.class);

		// 只要这个属性设置为true，不管有没有addInterface添加接口，都会使用cglib创建代理对象
		proxyFactory.setProxyTargetClass(true);
		Target1 proxy = (Target1)proxyFactory.getProxy();
		// 可以看到foo和bar方法被代理增强了
		proxy.foo();
		proxy.bar();
	}

	/**
	 * 最基础的事务原理测试
	 * 该方法的缺点是对于proxy类中的所有方法都走了切面，不能自己控制哪些方法切入哪些不切入
	 */
	public static void test01(){
		ProxyFactory proxyFactory = new ProxyFactory();
		// 设置管理的目标对象
		proxyFactory.setTarget(new Target1());
		// 添加通知，MethodInterceptor是一个环绕通知
		proxyFactory.addAdvice(new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("before.....");
				Object proceed = invocation.proceed();
				System.out.println("after.....");
				return proceed;
			}
		});
		Target1 proxy = (Target1)proxyFactory.getProxy();
		// 可以看到foo和bar方法被代理增强了
		proxy.foo();
		proxy.bar();
	}

	interface I1{
		void foo();
		void bar();
	}

	static class Target1 implements I1{

		@Override
		public void foo() {
			System.out.println("Target1 foo");
		}

		@Override
		public void bar() {
			System.out.println("Target1 bar");
		}
	}
}

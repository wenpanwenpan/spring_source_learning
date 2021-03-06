package com.wp.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @description:
 * @author: panfeng.wen@hand-china
 * @create: 2021/03/06 17:51
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 日志切点，抽取公共的切入点表达式。
     * 如果外部的切面类也向使用我们这里的切点，那么可以以全类命的方式引入
     * 该切点，比如：com.wp.spring.aop.aspects.LogAspect.pointCut()
     */
    @Pointcut("execution(public int com.wp.spring.aop.service.MathCalculatorServiceImpl.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        System.out.println("这是前置通知......." + methodName + "方法参数： " + Arrays.asList(methodArgs));
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("这是后置通知.......方法名称： " + methodName + "方法参数： " + Arrays.asList(args));
    }

    /**
     * 返回通知
     * @param result 目标方法返回值
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result){
        System.out.println("这是返回通知.......方法返回值：" + result);
    }

    /**
     * 异常通知
     * @param joinPoint 连接点，可以拿到方法参数，名称等信息
     */
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        System.out.println("这是异常通知......." + exception);
    }

    // @Around("pointCut()")
    public void logAround(){
        System.out.println("这是环绕通知.......");
    }
}

package com.wp.spring.test;

import com.wp.spring.aop.service.MathCalculatorServiceImpl;
import com.wp.spring.aop.config.MainConfigOfAop;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author panfeng.wen@hand-china 2021/03/06 17:58
 */
public class AopTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        MathCalculatorServiceImpl calculatorService = applicationContext.getBean(MathCalculatorServiceImpl.class);
        int div = calculatorService.div(1, 1);
        System.out.println("========div=" + div);
        applicationContext.close();
    }
}

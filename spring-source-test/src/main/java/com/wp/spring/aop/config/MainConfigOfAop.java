package com.wp.spring.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description: AOP测试主配置类
 * @author: panfeng.wen@hand-china
 * @create: 2021/03/06 17:55
 */
@Configuration
@ComponentScan("com.wp.spring")
@EnableAspectJAutoProxy
public class MainConfigOfAop {


}

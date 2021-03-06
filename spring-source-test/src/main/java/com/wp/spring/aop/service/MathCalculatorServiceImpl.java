package com.wp.spring.aop.service;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: panfeng.wen@hand-china
 * @create: 2021/03/06 17:54
 */
@Component
public class MathCalculatorServiceImpl {

    public int div(int i, int j){
        return i/j;
    }
}

package com.coco.cloud.spring.demo.aspect;


import com.coco.cloud.spring.framework.aop.aspect.CoCoJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author liuqiang@ourdocker.cn
 * AspectJ
 * 模拟aspectJ实现AOP
 * date : 2019-12-09
 */
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //在调用一个方法之前，执行before方法
    public void before(CoCoJoinPoint joinPoint){
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(),System.currentTimeMillis());
        //这个方法中的逻辑，是由自己写的
        logger.info("Invoker Before Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
    }

    //在调用一个方法之后，执行after方法
    public void after(CoCoJoinPoint joinPoint){
        logger.info("Invoker After Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
        long startTime = (Long) joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
        long endTime = System.currentTimeMillis();
        System.out.println("use time :" + (endTime - startTime));
    }

    public void afterThrowing(CoCoJoinPoint joinPoint, Throwable ex){
        logger.info("出现异常" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()) +
                "\nThrows:" + ex.getMessage());
    }

}

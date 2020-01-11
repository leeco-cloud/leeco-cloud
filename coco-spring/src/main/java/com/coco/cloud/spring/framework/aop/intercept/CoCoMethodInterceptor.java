package com.coco.cloud.spring.framework.aop.intercept;

/**
 * @author liuqiang@ourdocker.cn
 * MethodInterceptor
 * date : 2019-12-09
 */
public interface CoCoMethodInterceptor {

    Object invoke(CoCoMethodInvocation mi) throws Throwable;

}

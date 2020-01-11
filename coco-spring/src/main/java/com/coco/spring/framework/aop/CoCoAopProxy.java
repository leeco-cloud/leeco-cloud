package com.coco.spring.framework.aop;

/**
 * @author liuqiang@ourdocker.cn
 * AopProxy
 * AOP实现的顶层接口
 * date : 2019-12-09
 */
public interface CoCoAopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);

}

package com.coco.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author liuqiang@ourdocker.cn
 * JoinPoint
 * date : 2019-12-09
 */
public interface CoCoJoinPoint {

    Method getMethod();

    Object[] getArguments();

    Object getThis();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);

}

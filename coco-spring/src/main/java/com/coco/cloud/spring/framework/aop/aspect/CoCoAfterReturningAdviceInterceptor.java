package com.coco.cloud.spring.framework.aop.aspect;


import com.coco.cloud.spring.framework.aop.intercept.CoCoMethodInterceptor;
import com.coco.cloud.spring.framework.aop.intercept.CoCoMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author liuqiang@ourdocker.cn
 * AfterReturningAdviceInterceptor
 * date : 2019-12-09
 */
public class CoCoAfterReturningAdviceInterceptor extends CoCoAbstractAspectJAdvice implements CoCoAdvice, CoCoMethodInterceptor {

    private CoCoJoinPoint joinPoint;

    public CoCoAfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(CoCoMethodInvocation mi) throws Throwable {
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal,mi.getMethod(),mi.getArguments(),mi.getThis());
        return retVal;
    }

    private void afterReturning(Object retVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint,retVal,null);
    }

}

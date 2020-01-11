package com.coco.cloud.spring.framework.aop.aspect;

import com.coco.cloud.spring.framework.aop.intercept.CoCoMethodInterceptor;
import com.coco.cloud.spring.framework.aop.intercept.CoCoMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author liuqiang@ourdocker.cn
 * MethodBeforeAdviceInterceptor
 * date : 2019-12-09
 */
public class CoCoMethodBeforeAdviceInterceptor extends CoCoAbstractAspectJAdvice implements CoCoAdvice, CoCoMethodInterceptor {

    private CoCoJoinPoint joinPoint;

    public CoCoMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method,Object[] args,Object target) throws Throwable{
        //传送了给织入参数
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint,null,null);
    }

    @Override
    public Object invoke(CoCoMethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }

}

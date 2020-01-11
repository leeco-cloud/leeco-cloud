package com.coco.cloud.spring.framework.aop.aspect;

import com.coco.cloud.spring.framework.aop.intercept.CoCoMethodInterceptor;
import com.coco.cloud.spring.framework.aop.intercept.CoCoMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author liuqiang@ourdocker.cn
 * AfterThrowingAdviceInterceptor
 * date : 2019-12-09
 */
public class CoCoAfterThrowingAdviceInterceptor extends CoCoAbstractAspectJAdvice implements CoCoAdvice, CoCoMethodInterceptor {

    private String throwingName;

    public CoCoAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(CoCoMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable e){
            invokeAdviceMethod(mi,null,e.getCause());
            throw e;
        }
    }

    public void setThrowingName(String throwName){
        this.throwingName = throwName;
    }

}

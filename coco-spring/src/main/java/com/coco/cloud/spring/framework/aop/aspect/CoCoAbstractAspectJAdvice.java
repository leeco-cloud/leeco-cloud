package com.coco.cloud.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author liuqiang@ourdocker.cn
 * AbstractAspectJAdvice
 * 模拟aspectJ的核心实现
 * date : 2019-12-09
 */
public abstract class CoCoAbstractAspectJAdvice implements CoCoAdvice {

    private Method aspectMethod;

    private Object aspectTarget;

    public CoCoAbstractAspectJAdvice( Method aspectMethod, Object aspectTarget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }

    protected Object invokeAdviceMethod(CoCoJoinPoint joinPoint,Object returnValue,Throwable ex) throws Throwable {
        Class<?> [] paramsTypes = this.aspectMethod.getParameterTypes();
        if(paramsTypes.length == 0) {
            return this.aspectMethod.invoke(aspectTarget);
        }else {
            Object[] args = new Object[paramsTypes.length];
            for (int i = 0; i < paramsTypes.length; i++) {
                if(paramsTypes[i] == CoCoJoinPoint.class){
                    args[i] = joinPoint;
                }else if(paramsTypes[i] == Throwable.class){
                    args[i] = ex;
                }else if(paramsTypes[i] == Object.class){
                    args[i] = returnValue;
                }
            }return this.aspectMethod.invoke(aspectTarget,args);
        }
    }

}

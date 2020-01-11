package com.coco.spring.framework.aop;

import com.coco.spring.framework.aop.intercept.CoCoMethodInvocation;
import com.coco.spring.framework.aop.support.CoCoAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * JdkDynamicAopProxy
 * jdk方式实现代理的创建类
 * date : 2019-12-09
 */
public class CoCoJdkDynamicAopProxy implements CoCoAopProxy, InvocationHandler {

    private CoCoAdvisedSupport advised;

    public CoCoJdkDynamicAopProxy(CoCoAdvisedSupport advised){
        this.advised = advised;
    }

    /**
     * 把原生的对象传进来
     */
    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,this.advised.getTargetClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicMethodMatchers = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method,this.advised.getTargetClass());
        CoCoMethodInvocation invocation = new CoCoMethodInvocation(proxy,this.advised.getTarget(),method,args,this.advised.getTargetClass(),interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }
}

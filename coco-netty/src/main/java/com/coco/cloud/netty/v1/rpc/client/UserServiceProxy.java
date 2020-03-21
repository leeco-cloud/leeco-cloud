package com.coco.cloud.netty.v1.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 服务代理
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 21:13
 */
public class UserServiceProxy implements InvocationHandler {

    private Object realObject;

    UserServiceProxy(Object realObject){
        this.realObject = realObject;
    }

    <T> T getProxy(){
        return (T) Proxy.newProxyInstance(realObject.getClass().getClassLoader(),realObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(realObject, args);
        System.out.println("我是代理.");
        return invoke;
    }

}

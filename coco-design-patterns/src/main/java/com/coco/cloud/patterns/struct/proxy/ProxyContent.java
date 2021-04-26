package com.coco.cloud.patterns.struct.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 婚介中介 帮忙找女朋友
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 21:13
 */
public class ProxyContent implements InvocationHandler {

    private Object realObject;

    ProxyContent(Object realObject){
        this.realObject = realObject;
    }

    <T> T getProxy(){
        return (T) Proxy.newProxyInstance(realObject.getClass().getClassLoader(),realObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(realObject, args);
        System.out.println("所以由婚介中心帮忙找女朋友...");
        return invoke;
    }

}

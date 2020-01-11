package com.coco.spring.framework.beans;

/**
 * @author liuqiang@ourdocker.cn
 * BeanWrapper
 * 包装器模式 Bean的包装类 在bean初始化的时候 属性填充
 * 包括在创建AOP代理类的时候
 * 用完销毁
 * date : 2019-11-30
 */
public class CoCoBeanWrapper {

    private Object wrapperInstance;

    private Class<?> wrapperClass;

    public CoCoBeanWrapper(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Object getWrapperInstance(){
        return this.wrapperInstance;
    }

    /**
     * @return ProxyClass
     */
    public Class<?> getWrapperClass(){
        return this.wrapperInstance.getClass();
    }

}

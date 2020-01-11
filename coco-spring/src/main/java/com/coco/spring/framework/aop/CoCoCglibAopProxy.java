package com.coco.spring.framework.aop;

import com.coco.spring.framework.aop.support.CoCoAdvisedSupport;

/**
 * @author liuqiang@ourdocker.cn
 * CglibAopProxy
 * cglib方式实现代理的创建类
 * date : 2019-12-09
 */
public class CoCoCglibAopProxy implements CoCoAopProxy {

    private CoCoAdvisedSupport config;

    public CoCoCglibAopProxy(CoCoAdvisedSupport config){
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }

}

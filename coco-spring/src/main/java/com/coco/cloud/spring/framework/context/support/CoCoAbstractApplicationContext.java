package com.coco.cloud.spring.framework.context.support;

/**
 * @author liuqiang@ourdocker.cn
 * AbstractApplicationContext
 * IOC容器的顶层抽象类
 * date : 2019-11-30
 */
public abstract class CoCoAbstractApplicationContext {

    /**
     * IOC容器的核心方法 初始化容器
     * @throws Exception Exception
     */
    public void refresh() throws Exception{
        throw new RuntimeException("refresh AbstractApplicationContext fail...");
    }

}

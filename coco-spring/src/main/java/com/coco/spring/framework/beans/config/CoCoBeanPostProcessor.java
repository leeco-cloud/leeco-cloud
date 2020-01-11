package com.coco.spring.framework.beans.config;

/**
 * @author liuqiang@ourdocker.cn
 * BeanPostProcessor
 * 传说中的BPP模式
 * 围绕在在Bean的初始化过程
 * date : 2019-11-30
 */
public class CoCoBeanPostProcessor {

    /**
     * 为在 Bean 的初始化前提供回调入口
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    /**
     * 为在 Bean 的初始化后提供回调入口
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

}

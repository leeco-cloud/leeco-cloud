package com.coco.cloud.spring.framework.context.support;

import com.coco.cloud.spring.framework.beans.config.CoCoBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuqiang@ourdocker.cn
 * DefaultListableBeanFactory
 * IOC容器的默认实现
 * date : 2019-11-30
 */
public class CoCoDefaultListableBeanFactory extends CoCoAbstractApplicationContext {

    /**
     * 存储注册信息的 BeanDefinition
     */
    protected final Map<String, CoCoBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, CoCoBeanDefinition>();

}
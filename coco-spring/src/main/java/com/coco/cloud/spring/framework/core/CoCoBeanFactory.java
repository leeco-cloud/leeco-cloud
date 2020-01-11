package com.coco.cloud.spring.framework.core;

/**
 * @author liuqiang@ourdocker.cn
 * BeanFactory顶层接口
 * date : 2019-11-30
 */
public interface CoCoBeanFactory {

    /**
     * 根据beanName获取Bean对象
     * bean为懒加载的时候 在初次调用该方式时初始化bean
     * bean为非懒加载的时候 在初始化IOC容器的时候就初始化Bean
     * @param beanName beanName
     * @return Bean对象
     */
    Object getBean(String beanName);

    /**
     * 根据Class<?>获取Bean对象
     * @param clazz clazz
     * @return Bean对象
     */
    Object getBean(Class<?> clazz);

}

package com.coco.spring.framework.beans.config;


/**
 * @author liuqiang@ourdocker.cn
 * BeanDefinition
 * 在发现注册bean的时候 存储bean的信息
 * date : 2019-11-30
 */
public class CoCoBeanDefinition{

    /**
     * beanClassName
     */
    private String beanClassName;

    /**
     * this bean is lazy init?
     */
    private Boolean isLazyInit = false;

    /**
     * factoryBeanName
     */
    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public Boolean getLazyInit() {
        return isLazyInit;
    }

    public void setLazyInit(Boolean lazyInit) {
        isLazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}

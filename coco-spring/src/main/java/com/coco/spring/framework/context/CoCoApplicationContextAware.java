package com.coco.spring.framework.context;

/**
 * @author liuqiang@ourdocker.cn
 * ApplicationContextAware
 * 通过解耦方式获得 IOC 容器的顶层设计
 * 后面将通过一个监听器去扫描所有的类，只要实现了此接口，
 * 将自动调用 setApplicationContext()方法，从而将 IOC 容器注入到目标类中
 * date : 2019-11-30
 */
public interface CoCoApplicationContextAware {

    /**
     * setApplicationContext
     * 需要注意的是 spring容器中往往存在多个ApplicationContext容器
     * 所以需要清楚想要获取的是哪一个容器
     * ApplicationContext / WebApplicationContext / ...
     * @param context ApplicationContext
     */
    void setApplicationContext(CoCoApplicationContext context);

}

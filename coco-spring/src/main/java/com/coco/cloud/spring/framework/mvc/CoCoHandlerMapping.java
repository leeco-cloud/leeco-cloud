package com.coco.cloud.spring.framework.mvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author liuqiang@ourdocker.cn
 * HandlerMapping
 * 保存controller中method与url的关系
 */
public class CoCoHandlerMapping {

    /**
     * 保存方法对应的实例
     */
    private Object controller;

    /**
     * 保存映射的方法
     */
    private Method method;

    /**
     * URL的正则匹配
     */
    private Pattern pattern;

    public CoCoHandlerMapping(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}

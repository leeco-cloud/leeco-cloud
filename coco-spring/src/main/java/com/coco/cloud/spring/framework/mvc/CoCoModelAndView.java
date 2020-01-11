package com.coco.cloud.spring.framework.mvc;

import java.util.Map;

/**
 * @author liuqiang@ourdocker.cn
 * ModelAndView
 * 模板试图
 */
public class CoCoModelAndView {

    private String viewName;

    private Map<String,?> model;

    public CoCoModelAndView(String viewName) { this.viewName = viewName; }

    public CoCoModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

}

package com.coco.cloud.design.patterns.adapter;

import java.util.Map;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:31
 */
public interface ThirdLogin {

    /**
     * 第三方登陆通用入口
     * @param param map
     */
    public void loginByThird(Map<String,String> param);

}

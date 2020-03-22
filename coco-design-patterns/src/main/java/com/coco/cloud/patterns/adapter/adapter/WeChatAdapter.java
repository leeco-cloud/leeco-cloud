package com.coco.cloud.patterns.adapter.adapter;

import com.coco.cloud.patterns.adapter.AbstractThirdLogin;

import java.util.Map;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:32
 */
public class WeChatAdapter extends AbstractThirdLogin {

    @Override
    public void loginByThird(Map<String,String> param) {
        login(param.get("token"),param.get("openId"));
    }

}

package com.coco.cloud.design.patterns.adapter;


import com.coco.cloud.design.patterns.adapter.adapter.AliAdapter;
import com.coco.cloud.design.patterns.adapter.adapter.QQAdapter;
import com.coco.cloud.design.patterns.adapter.adapter.WeChatAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:39
 */
public class Test {

    ThirdLogin thirdLogin = new QQAdapter();
    Map<String,String> param = new HashMap<>();
    thirdLogin.loginByThird(param);

    ThirdLogin aliLogin = new AliAdapter();
    Map<String,String> paramAli = new HashMap<>();
    thirdLogin.loginByThird(paramAli);

    ThirdLogin thirdLogin = new WeChatAdapter();
    Map<String,String> paramWeChat = new HashMap<>();
    thirdLogin.loginByThird(paramWeChat);

}

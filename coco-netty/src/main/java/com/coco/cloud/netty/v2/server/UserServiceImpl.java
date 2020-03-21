package com.coco.cloud.netty.v2.server;

import com.coco.cloud.netty.v2.api.UserService;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/21 18:48
 */
public class UserServiceImpl implements UserService {

    @Override
    public String hello(String context) {
        return "hello " + context;
    }

}

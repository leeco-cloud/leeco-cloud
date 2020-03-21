package com.coco.cloud.netty.v2.client;

import com.coco.cloud.netty.v2.api.UserService;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/21 19:45
 */
public class RpcClient {

    public static void main(String[] args) {
        UserService userService = (UserService) new RpcProxy().getProxy(UserService.class);
        System.out.println(userService.hello("lee co"));

    }

}

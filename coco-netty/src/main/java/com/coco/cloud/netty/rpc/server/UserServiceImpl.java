package com.coco.cloud.netty.rpc.server;

import com.coco.cloud.netty.rpc.UserService;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 20:16
 */
public class UserServiceImpl implements UserService {

    @Override
    public void showName() {
        System.out.println("UserServiceImpl");
    }

}

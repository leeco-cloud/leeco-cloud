package com.coco.cloud.design.patterns.adapter;

/**
 * 基础账号密码登陆
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:29
 */
public class PasswordLogin {

    public void login(String userName,String password){
        System.out.println("账号密码登陆:userName : " + userName);
    }

}

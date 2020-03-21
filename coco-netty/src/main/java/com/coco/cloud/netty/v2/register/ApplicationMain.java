package com.coco.cloud.netty.v2.register;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/21 19:05
 */
public class ApplicationMain {

    public static void main(String[] args) {
        try {
            new Register(8080).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

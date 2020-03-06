package com.coco.cloud.design.patterns.proxy;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 21:17
 */
public class Test {

    public static void main(String[] args) {
        LonelyDog proxy = new ProxyContent(new LonelyDogBoy()).getProxy();
        proxy.findGirlFriend();
    }

}

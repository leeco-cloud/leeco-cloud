package com.coco.cloud.design.patterns.proxy;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 21:12
 */
public class LonelyDogBoy implements LonelyDog {

    @Override
    public void findGirlFriend() {
        System.out.println("单身狗好不到女朋友...");
    }

}

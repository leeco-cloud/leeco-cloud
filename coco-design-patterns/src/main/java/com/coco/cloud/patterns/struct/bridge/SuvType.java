package com.coco.cloud.patterns.struct.bridge;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:01
 */
public class SuvType implements Type {
    @Override
    public void type() {
        System.out.println("我是SUV");
    }
}

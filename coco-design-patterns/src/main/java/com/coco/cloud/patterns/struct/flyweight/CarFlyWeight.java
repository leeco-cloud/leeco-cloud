package com.coco.cloud.patterns.struct.flyweight;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:26
 */
public class CarFlyWeight implements Car {

    private String name;

    public CarFlyWeight(String name) {
        this.name = name;
    }

    @Override
    public void sendCar(String state) {
        System.out.println("我来租车了,租的是: " + name + ", 我是: " + state);
    }
}

package com.coco.cloud.patterns.struct.adapter;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 21:20
 */
public class Test {

    public static void main(String[] args) {
        Car car = new BmwAdapterCar();
        car.say();
    }

}

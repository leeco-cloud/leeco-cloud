package com.coco.cloud.patterns.struct.flyweight;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:31
 */
public class Test {

    public static void main(String[] args) {
        Car car = new CarFlyWeightFactory();
        car.sendCar("张三");
        car.sendCar("李四");
        car.sendCar("王五");
        car.sendCar("周六");
    }
}

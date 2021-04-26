package com.coco.cloud.patterns.create.factory.simpleFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:53
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Car car = simpleFactory.getCar(BenCar.class);
        car.say();
        Car car2 = simpleFactory.getCar(BmwCar.class);
        car2.say();
    }

}

package com.coco.cloud.patterns.create.factory.factoryMethod;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:53
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        AbstractFactoryMethod abstractFactoryMethod = new BenFactoryMethod();
        Car car = abstractFactoryMethod.getCar();
        car.say();

        AbstractFactoryMethod abstractFactoryMethod2 = new BmwFactoryMethod();
        Car car2 = abstractFactoryMethod2.getCar();
        car2.say();
    }

}

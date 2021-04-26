package com.coco.cloud.patterns.create.factory.factoryMethod;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:49
 */
public class BmwFactoryMethod implements AbstractFactoryMethod {

    @Override
    public Car getCar() {
        return new BmwCar();
    }

}

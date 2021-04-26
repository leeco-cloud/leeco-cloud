package com.coco.cloud.patterns.create.factory.factoryMethod;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:50
 */
public class BmwCar implements Car {

    @Override
    public void say() {
        System.out.println("我是宝马...");
    }

}

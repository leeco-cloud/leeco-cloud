package com.coco.cloud.patterns.create.factory.simpleFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:50
 */
public class BenCar implements Car{

    @Override
    public void say() {
        System.out.println("我是奔驰...");
    }

}

package com.coco.cloud.patterns.struct.facade;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 21:10
 */
public class Car {

    public void say(){
        new BenCar().say();
        new BmwCar().say();
    }

}

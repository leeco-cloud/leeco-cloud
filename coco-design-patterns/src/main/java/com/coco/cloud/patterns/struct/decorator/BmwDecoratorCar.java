package com.coco.cloud.patterns.struct.decorator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 21:18
 */
public class BmwDecoratorCar implements Car {

    private Car car;

    public BmwDecoratorCar(Car car) {
        this.car = car;
    }

    @Override
    public void say(){
        car.say();
        System.out.println("装饰宝马");
    }

}

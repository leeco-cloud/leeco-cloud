package com.coco.cloud.patterns.behavior.chain;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:43
 */
public abstract class AbstractCar implements Car {

    protected Car car;

    public AbstractCar(Car car) {
        this.car = car;
    }

    @Override
    public void check() {
        if (car != null){
            car.check();
        }
    }
}

package com.coco.cloud.patterns.behavior.strategy;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 21:47
 */
public class StrategyContext {

    Car car;

    public StrategyContext(Car car) {
        this.car = car;
    }

    public void buyCar(){
        car.buyCar();
    }

}

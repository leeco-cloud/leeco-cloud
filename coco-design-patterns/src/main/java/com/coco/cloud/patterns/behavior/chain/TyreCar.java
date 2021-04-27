package com.coco.cloud.patterns.behavior.chain;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:43
 */
public class TyreCar extends AbstractCar {


    public TyreCar(Car car) {
        super(car);
    }

    @Override
    public void check() {
        System.out.println("检测轮胎");
        super.check();
    }

}

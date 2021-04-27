package com.coco.cloud.patterns.behavior.chain;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:43
 */
public class DisplacementCar extends AbstractCar {

    public DisplacementCar(Car car) {
        super(car);
    }

    @Override
    public void check() {
        System.out.println("排量检测");
        super.check();
    }

}

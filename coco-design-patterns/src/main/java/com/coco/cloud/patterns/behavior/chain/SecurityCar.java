package com.coco.cloud.patterns.behavior.chain;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:43
 */
public class SecurityCar extends AbstractCar {


    public SecurityCar(Car car) {
        super(car);
    }

    @Override
    public void check() {
        System.out.println("安全检测");
        super.check();
    }

}

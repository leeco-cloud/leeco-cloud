package com.coco.cloud.patterns.struct.bridge;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:02
 */
public class BridgeCar implements Car,Type {

    private Car car;

    private Type type;

    public BridgeCar(Car car, Type type) {
        this.car = car;
        this.type = type;
    }


    @Override
    public void car() {
        car.car();
    }

    @Override
    public void type() {
        type.type();
    }
}

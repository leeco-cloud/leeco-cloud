package com.coco.cloud.patterns.struct.bridge;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 22:03
 */
public class Test {

    public static void main(String[] args) {
        Car car = new BmwCar();
        Type type = new CityType();
        BridgeCar bridgeCar = new BridgeCar(car, type);
        bridgeCar.car();
        bridgeCar.type();
    }

}

package com.coco.cloud.patterns.behavior.chain;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:51
 */
public class ChainTest {

    public static void main(String[] args) {
        Car securityCar = new SecurityCar(null);
        Car tyreCar = new TyreCar(securityCar);
        Car displacementCar = new DisplacementCar(tyreCar);

        displacementCar.check();
    }

}

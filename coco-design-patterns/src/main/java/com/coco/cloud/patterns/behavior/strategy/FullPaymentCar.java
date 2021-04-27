package com.coco.cloud.patterns.behavior.strategy;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 21:46
 */
public class FullPaymentCar implements Car {

    @Override
    public void buyCar() {
        System.out.println("全款买车");
    }

}

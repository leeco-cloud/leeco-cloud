package com.coco.cloud.patterns.behavior.template;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:58
 */
public class CityCar extends AbstractCar {
    @Override
    protected void type() {
        System.out.println("City");
    }
}

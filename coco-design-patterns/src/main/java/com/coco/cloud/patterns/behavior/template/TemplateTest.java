package com.coco.cloud.patterns.behavior.template;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:58
 */
public class TemplateTest {

    public static void main(String[] args) {
        AbstractCar car = new CityCar();
        car.buyCar();
        car = new SuvCar();
        car.buyCar();
    }

}

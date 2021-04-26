package com.coco.cloud.patterns.create.factory.simpleFactory;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 20:49
 */
public class SimpleFactory {

    public Car getCar(Class car){
        if (car == BmwCar.class){
            return new BmwCar();
        }
        if (car  ==  BenCar.class){
            return new BenCar();
        }
        return null;
    }

}

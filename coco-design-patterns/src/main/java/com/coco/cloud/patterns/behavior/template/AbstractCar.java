package com.coco.cloud.patterns.behavior.template;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 20:56
 */
public abstract class AbstractCar {

    public void buyCar(){
        System.out.println("买车");
        type();
    }

    protected abstract void type();

}

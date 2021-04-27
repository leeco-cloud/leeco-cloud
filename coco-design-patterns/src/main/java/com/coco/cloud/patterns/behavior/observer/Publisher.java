package com.coco.cloud.patterns.behavior.observer;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:44
 */
public class Publisher extends AbstractPublisher {

    public void finish(){
        System.out.println("被观察者发生了动作");
        super.publishEvent();
    }

}

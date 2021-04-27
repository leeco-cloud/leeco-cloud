package com.coco.cloud.patterns.behavior.observer;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:40
 */
public class ObserverB implements Subscriber {
    @Override
    public void lisenPublish() {
        System.out.println("A接收到了变化");
    }
}

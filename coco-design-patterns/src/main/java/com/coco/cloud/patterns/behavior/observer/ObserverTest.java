package com.coco.cloud.patterns.behavior.observer;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:45
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subscriber observerA = new ObserverA();
        Subscriber observerB = new ObserverA();

        Publisher publish = new Publisher();
        publish.addObserver(observerA);
        publish.addObserver(observerB);

        publish.finish();
    }
}

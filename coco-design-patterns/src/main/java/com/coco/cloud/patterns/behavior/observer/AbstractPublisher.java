package com.coco.cloud.patterns.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 22:39
 */
public abstract class AbstractPublisher {

    private List<Subscriber> subscriberList = new ArrayList<>();

    public void addObserver(Subscriber subscriber){
        subscriberList.add(subscriber);
    }

    void publishEvent(){
        for (Subscriber subscriber : subscriberList) {
            subscriber.lisenPublish();
        }
    }
}

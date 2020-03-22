package com.coco.cloud.patterns.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/28 18:18
 */
public class ContainSingleton {

    private final static Map<String, ContainSingleton> singletonMap = new ConcurrentHashMap<>();

    public static ContainSingleton getInstance(String key) {
        ContainSingleton containSingleton = singletonMap.get(key);
        if (containSingleton == null){
            synchronized (singletonMap){
                containSingleton = new ContainSingleton();
                singletonMap.put(key,containSingleton);
            }
        }
        return containSingleton;
    }

}

package com.coco.cloud.design.patterns.singleton;

/**
 * 饿汉式单例模式
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/28 18:15
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){
        try {
            throw new Exception("不允许破坏单例");
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

    /**
     * 防止反序列化破坏单例
     */
    public Object readResolve(){
        return instance;
    }

}

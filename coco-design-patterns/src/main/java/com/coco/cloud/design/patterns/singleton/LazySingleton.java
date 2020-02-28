package com.coco.cloud.design.patterns.singleton;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/28 18:17
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){
        try {
            throw new Exception("不允许破坏单例");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LazySingleton getInstance(){
        if (instance == null){
            synchronized (LazySingleton.class){
                if (instance == null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    /**
     * 防止反序列化破坏单例
     */
    public Object readResolve(){
        return instance;
    }

}

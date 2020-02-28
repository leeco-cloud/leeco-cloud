package com.coco.cloud.design.patterns.singleton;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/2/28 18:18
 */
public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){
        try {
            throw new Exception("不允许破坏单例");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ThreadLocal<ThreadLocalSingleton> threadLocalSingleton = new ThreadLocal<>();

    public static ThreadLocalSingleton getInstance(ThreadLocalSingleton threadLocalSingleton2){
        if (threadLocalSingleton.get() == null){
            threadLocalSingleton.set(new ThreadLocalSingleton());
        }
        return threadLocalSingleton.get();
    }

    /**
     * 防止反序列化破坏单例
     */
    public Object readResolve(){
        return threadLocalSingleton.get();
    }

}

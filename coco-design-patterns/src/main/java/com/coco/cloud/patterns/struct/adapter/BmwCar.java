package com.coco.cloud.patterns.struct.adapter;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 21:18
 */
public class BmwCar implements Car {

    @Override
    public void say() {
        System.out.println("我是宝马");
    }

}

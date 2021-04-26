package com.coco.cloud.patterns.struct.bridge;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 21:59
 */
public class BmwCar implements Car{

    @Override
    public void car() {
        System.out.println("我是宝马");
    }

}

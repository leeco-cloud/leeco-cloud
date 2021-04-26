package com.coco.cloud.patterns.struct.adapter;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/26 21:18
 */
public class BmwAdapterCar extends BmwCar implements Car {

    @Override
    public void say(){
        super.say();
        System.out.println("我适配了宝马");
    }

}

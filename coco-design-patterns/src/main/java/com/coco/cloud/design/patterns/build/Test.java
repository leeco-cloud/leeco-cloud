package com.coco.cloud.design.patterns.build;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/6 21:32
 */
public class Test {

    public static void main(String[] args) {
        Cake cake = new Cake();
        CakeBuild.build(cake).addEgg().addMeat();

    }

}
